package no.soprasteria.bomsystemet.beregning;

import org.springframework.stereotype.Component;

import no.soprasteria.bomsystemet.database.Forbipasseringsregister;
import no.soprasteria.bomsystemet.oppslag.kjøretøy.KjøretøyOppslagKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Sone;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;
import no.soprasteria.felles.kontrakter.bomsystem.krav.Grunnlag;

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

    public int beregnVeiavgift(Grunnlag beregningsgrunnlag) {
        var registreringsnummer = beregningsgrunnlag.forbipasseringer().get(0).registreringsnummer();
        var sone = beregningsgrunnlag.forbipasseringer().get(0).forbipasseringsinformasjon().sone();

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
