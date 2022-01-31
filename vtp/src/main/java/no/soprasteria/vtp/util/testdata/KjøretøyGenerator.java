package no.soprasteria.vtp.util.testdata;

import static no.soprasteria.vtp.util.testdata.kjøretøy.KjøretøyKlasseGenerator.randomKjøretøyKlasse;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Eier;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Kjøretøy;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;
import no.soprasteria.vtp.util.testdata.kjøretøy.RegistreringsnummerGenerator;

import java.util.Objects;


public class KjøretøyGenerator {

    private KjøretøyGenerator() {
    }

    public static Kjøretøy lagFiktivtKjøretøy(Fødselsnummer fødselsnummerTilEier) {
        return lagFiktivtKjøretøy(fødselsnummerTilEier, randomKjøretøyKlasse());
    }

    public static Kjøretøy lagFiktivtKjøretøy(Fødselsnummer fødselsnummerTilEier, KjøretøyKlasse kjøretøyKlasse) {
        return new Kjøretøy(
                new Registreringsnummer(
                        Objects.requireNonNull(RegistreringsnummerGenerator.getRandomRegistreringsnummer())),
                new KjøretøyInfo(new Eier(fødselsnummerTilEier),
                        kjøretøyKlasse)
        );
    }
}
