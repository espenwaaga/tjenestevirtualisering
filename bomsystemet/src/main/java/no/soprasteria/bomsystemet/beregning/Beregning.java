package no.soprasteria.bomsystemet.beregning;

import org.springframework.stereotype.Component;

import no.soprasteria.bomsystemet.util.database.Bompasseringsregister;
import no.soprasteria.bomsystemet.oppslag.KjøretøyOppslagKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Sone;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;
import no.soprasteria.felles.kontrakter.bomsystem.krav.Grunnlag;

@Component
public class Beregning {
    private final Bompasseringsregister bompasseringsregister;
    private final KjøretøyOppslagKlient oppslag;

    public Beregning(Bompasseringsregister bompasseringsregister, KjøretøyOppslagKlient oppslag) {
        this.bompasseringsregister = bompasseringsregister;
        this.oppslag = oppslag;
    }

    public int beregnVeiavgift(Registreringsnummer registreringsnummer) {
        var bompasseringer = bompasseringsregister.get(registreringsnummer);
        var kjøretøyInfo = oppslag.hentInformasjonOmKjøretøy(registreringsnummer).kjøretøyKlasse();

        int avgift = 0;
        for (var bompassering : bompasseringer) {
            var sats = hentSats(bompassering.sone(), kjøretøyInfo);
            avgift += sats; // TODO: Legg til sats bare hvis det har gått mer enn 1 time siden sist passering
        }

        return avgift;
    }

    public int beregnVeiavgift(Grunnlag beregningsgrunnlag) {
        var registreringsnummer = beregningsgrunnlag.bompasseringer().get(0).registreringsnummer();
        var sone = beregningsgrunnlag.bompasseringer().get(0).bompasseringsinformasjon().sone();

        var kjøretøyKlasse = oppslag.hentInformasjonOmKjøretøy(registreringsnummer).kjøretøyKlasse();
        return hentSats(sone, kjøretøyKlasse);
     }

    private int hentSats(Sone sone, KjøretøyKlasse kjøretøyKlasse) {
        return satsForKjøretøy(kjøretøyKlasse); //+ soneGebyr(sone);
    }

    private int soneGebyr(Sone sone) {
        return switch (sone) {
            case SONE1 -> 5;
            case SONE2, SONE3 -> 3;
        };
    }

    private int satsForKjøretøy(KjøretøyKlasse kjøretøyKlasse) {
        return switch (kjøretøyKlasse) {
            case KLASSE1 -> 12;
            case KLASSE2 -> 24;
            case KLASSE3 -> 30;
        };
    }
}
