package no.soprasteria.bomsystemet.kravbehandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.bomsystemet.beregning.Beregning;
import no.soprasteria.bomsystemet.oppslag.kjøretøy.KjøretøyOppslagKlient;
import no.soprasteria.bomsystemet.oppslag.person.PersonOppslagKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;

public class Kravbehandler {
    private static final Logger LOG = LoggerFactory.getLogger(Kravbehandler.class);

    private final KjøretøyOppslagKlient kjøretøyOppslag;
    private final PersonOppslagKlient personOppslag;
    private final Beregning beregning;

    public Kravbehandler(PersonOppslagKlient personOppslag,
                         KjøretøyOppslagKlient kjøretøyOppslag,
                         Beregning beregning) {
        this.personOppslag = personOppslag;
        this.kjøretøyOppslag = kjøretøyOppslag;
        this.beregning = beregning;
    }

    public Krav opprettKravPåRegistreringsnummer(Registreringsnummer registreringsnummer) {
        LOG.info("Oppretter krav på {}", registreringsnummer);
        var beregnetVeiavgift = beregning.beregnVeiavgift(registreringsnummer);
        var fnr = kjøretøyOppslag.hentInformasjonOmKjøretøy(registreringsnummer).eier().fnr();
        var personInformasjon = personOppslag.hentOpplysninger(fnr);

        return new Krav(fnr, personInformasjon.epost(), beregnetVeiavgift);
    }
}
