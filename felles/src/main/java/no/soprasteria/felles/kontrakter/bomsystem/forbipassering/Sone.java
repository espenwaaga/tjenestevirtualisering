package no.soprasteria.felles.kontrakter.bomsystem.forbipassering;

import java.util.List;
import java.util.Random;

public enum Sone {
    SONE1,
    SONE2,
    SONE3;

    private static final List<Sone> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Sone random() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
