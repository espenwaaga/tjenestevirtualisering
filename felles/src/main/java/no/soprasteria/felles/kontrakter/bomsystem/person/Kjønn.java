package no.soprasteria.felles.kontrakter.bomsystem.person;

import java.util.List;
import java.util.Random;

public enum Kjønn {
    M,
    K;

    private static final List<Kjønn> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Kjønn randomKjonn() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
