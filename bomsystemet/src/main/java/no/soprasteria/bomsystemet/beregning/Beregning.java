package no.soprasteria.bomsystemet.beregning;

import org.springframework.stereotype.Component;

import no.soprasteria.bomsystemet.oppslag.kjøretøy.KjøretøyOppslagKlient;
import no.soprasteria.bomsystemet.register.Forbipasseringsregister;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Sone;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;

@Component
public class Beregning {
    private final Forbipasseringsregister forbipasseringsregister;
    private final KjøretøyOppslagKlient oppslag;

    public Beregning(Forbipasseringsregister forbipasseringsregister, KjøretøyOppslagKlient oppslag) {
        this.forbipasseringsregister = forbipasseringsregister;
        this.oppslag = oppslag;
    }

    public int beregnVeiavgift(Registreringsnummer registreringsnummer) {
        var forbipasseringer = forbipasseringsregister.get(registreringsnummer);
        var kjøretøyInfo = oppslag.hentInformasjonOmKjøretøy(registreringsnummer).kjøretøyKlasse();

        int avgift = 0;
        for (var forbipassering : forbipasseringer) {
            var sats = hentSats(forbipassering.sone(), kjøretøyInfo);
            avgift += sats; // TODO: Legg til sats bare hvis det har gått mer enn 1 time siden sist passering
        }

        return avgift;
    }

    private int hentSats(Sone sone, KjøretøyKlasse kjøretøyInfo) {
        return 24; // TODO
    }
}
