package no.soprasteria.bomsystemet.kravbehandling;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import no.soprasteria.bomsystemet.beregning.Beregning;
import no.soprasteria.bomsystemet.util.database.Kravregister;
import no.soprasteria.bomsystemet.oppslag.KjøretøyOppslagKlient;
import no.soprasteria.bomsystemet.oppslag.PersonOppslagKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Bompassering;
import no.soprasteria.felles.kontrakter.bomsystem.krav.Grunnlag;
import no.soprasteria.felles.kontrakter.bomsystem.krav.Krav;
import no.soprasteria.felles.kontrakter.bomsystem.krav.Periode;

@Component
public class Kravbehandler {
    private static final Logger LOG = LoggerFactory.getLogger(Kravbehandler.class);

    private final KjøretøyOppslagKlient kjøretøyOppslag;
    private final PersonOppslagKlient personOppslag;
    private final Beregning beregning;
    private final Kravregister kravregister;

    @Autowired
    public Kravbehandler(KjøretøyOppslagKlient kjøretøyOppslag, PersonOppslagKlient personOppslag,
                         Beregning beregning, Kravregister kravregister) {
        this.kjøretøyOppslag = kjøretøyOppslag;
        this.personOppslag = personOppslag;
        this.beregning = beregning;
        this.kravregister = kravregister;
    }

    /**
     * Antar at bompasseringer som mottas av registeringsapplikasjonen kommer i kronologisk rekkefølge.
     * Denne kan opprette ugyldig krav hvis den mottar passering tilbake i tid og den har mottatt passeringer tidligere
     * som er etter dette tidspunktet.
     */
    public void opprettKravPåPassering(Bompassering bompassering) {
        var fnr = kjøretøyOppslag.hentInformasjonOmKjøretøy(bompassering.registreringsnummer()).eier().fnr();
        var eksisterendeKrav = kravregister.getEksisterendeKravForPasseringstidspunkt(fnr, bompassering.bompasseringsinformasjon().tidspunkt());
        if (eksisterendeKrav == null) {
            lagNyttKrav(fnr, bompassering);
        } else {
            LOG.info("Tidligere passeringer er innen 1 time og avgiften er derfor 0");
            eksisterendeKrav.beregningsgrunnlag().bompasseringer().add(bompassering);
        }
    }

    //  ----------------------------------------------
    // Under illustrerer * bompasseringer og [      ] er et krav
    // Ingen endring
    //            [*      ]
    //                 *
    //         => [*   *  ]
    //  ----------------------------------------------
    // Nytt krav
    //            [*      ]
    //                        *
    //         => [*      ]  [*      ]
    //  ----------------------------------------------
    // Forskyvning
    //            [*      ]
    //         *
    //      =>[*   *  ]
    //  ----------------------------------------------
    // Forskyvning + nytt krav
    //            [*     *]
    //         *
    //      =>[*   *  ] [*     ]
    /**
     * Denne skal tåle bompasseringer i vilkårlig rekkefølge. Dvs. den tar høyde for forsinkelser eller feil i
     * innsending av bompasseringer.
     * F.eks. Person kjører gjennom bom 1 og en halvtime etterpå kjøre personen gjennom bom 2. Det er en feil med bom 1
     * som fører til at den ikke har sendt inn passering. Bom 2 fungere som normal og sender dermed inn med en gang.
     * Problemet med bom 1 fikses og den sender videre alle passeringer som har inntruffet den siste halvtimen.
     */
    public void opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(Bompassering bompassering) {
        var fnr = kjøretøyOppslag.hentInformasjonOmKjøretøy(bompassering.registreringsnummer()).eier().fnr();
        var bompasseringstidspunkt = bompassering.bompasseringsinformasjon().tidspunkt();
        var eksisterendeKrav = kravregister.getEksisterendeKravForPasseringstidspunkt(fnr, bompasseringstidspunkt);

        if (eksisterendeKrav == null) {
            var krav = lagNyttKrav(fnr, bompassering);
            kravregister.forskyvEksisterendePerioderVedOverlapp(fnr, krav);
        } else {
            eksisterendeKrav.beregningsgrunnlag().bompasseringer().add(bompassering);
        }
    }

    private Krav lagNyttKrav(Fødselsnummer fnr, Bompassering bompassering) {
        LOG.info("Oppretter krav på person {} registert på {}", fnr, bompassering.registreringsnummer());
        var bompasseringer = new ArrayList<Bompassering>();
        bompasseringer.add(bompassering);
        var beregningsgrunnlag = new Grunnlag(bompasseringer);
        var avgift = beregning.beregnVeiavgift(beregningsgrunnlag);
        var personInformasjon = personOppslag.hentOpplysninger(fnr);
        if (personInformasjon == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person med fødselsnummer " + fnr + "finnes ikke i registeret til skattetaten!");
        }

        var bompasseringstidspunkt = bompassering.bompasseringsinformasjon().tidspunkt();
        var gyldighetsperiode = new Periode(bompasseringstidspunkt, bompasseringstidspunkt.plusHours(1));
        var krav = new Krav(personInformasjon, gyldighetsperiode, beregningsgrunnlag, avgift);
        kravregister.add(fnr, krav);
        return krav;
    }
}
