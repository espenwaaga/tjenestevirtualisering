package no.soprasteria.vtp.testdataGenerator;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Eier;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Kjøretøy;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;
import no.soprasteria.vtp.testdataGenerator.util.RegistreringsnummerGenerator;

import java.util.Objects;

import static no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse.randomKjøretøyKlasse;

public class KjøretøyGenerator {

    private KjøretøyGenerator() {
    }

    public static Kjøretøy lagFiktivtKjøretøy(Fødselsnummer fødselsnummerTilEier) {
        return new Kjøretøy(
                new Registreringsnummer(
                        Objects.requireNonNull(RegistreringsnummerGenerator.getRandomRegistreringsnummer())),
                new KjøretøyInfo(new Eier(fødselsnummerTilEier),
                        randomKjøretøyKlasse())
                );
    }
}
