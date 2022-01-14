package no.soprasteria.felles.kontrakter.bomsystem.kjøretøy;

import java.util.List;
import java.util.Random;

public enum KjøretøyKlasse {
    KLASSE1,
    KLASSE2,
    KLASSE3;

    private static final List<KjøretøyKlasse> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static KjøretøyKlasse randomKjøretøyKlasse() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
