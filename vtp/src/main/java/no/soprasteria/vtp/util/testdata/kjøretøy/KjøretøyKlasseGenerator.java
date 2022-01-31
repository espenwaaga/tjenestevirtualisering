package no.soprasteria.vtp.util.testdata.kjøretøy;

import java.util.List;
import java.util.Random;

import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;

public class KjøretøyKlasseGenerator {

    private static final List<KjøretøyKlasse> VALUES = List.of(KjøretøyKlasse.values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    private KjøretøyKlasseGenerator() {
    }

    public static KjøretøyKlasse randomKjøretøyKlasse() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
