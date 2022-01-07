package no.soprasteria.bomsystemet.kravbehandling;

import no.soprasteria.bomsystemet.beregning.Beregning;
import no.soprasteria.bomsystemet.oppslag.kjøretøy.KjøretøyOppslagKlient;
import no.soprasteria.bomsystemet.oppslag.person.PersonOppslagKlient;
import no.soprasteria.bomsystemet.register.Forbipasseringsregister;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;

public class Kravbehandler {
    private final Forbipasseringsregister forbipasseringsregister;
    private final KjøretøyOppslagKlient kjøretøyOppslag;
    private final PersonOppslagKlient personOppslag;
    private final Beregning beregning;

    public Kravbehandler(Forbipasseringsregister forbipasseringsregister,
                         PersonOppslagKlient personOppslag,
                         KjøretøyOppslagKlient kjøretøyOppslag,
                         Beregning beregning) {
        this.forbipasseringsregister = forbipasseringsregister;
        this.personOppslag = personOppslag;
        this.kjøretøyOppslag = kjøretøyOppslag;
        this.beregning = beregning;
    }

    public Krav opprettKravPåRegistreringsnummer(Registreringsnummer registreringsnummer) {
        var beregnetVeiavgift = beregning.beregnVeiavgift(registreringsnummer);
        var fnr = kjøretøyOppslag.hentInformasjonOmKjøretøy(registreringsnummer).eier().fnr();
        var personInformasjon = personOppslag.hentOpplysninger(fnr);

        return new Krav(fnr, personInformasjon.epost(), beregnetVeiavgift);
    }
}
