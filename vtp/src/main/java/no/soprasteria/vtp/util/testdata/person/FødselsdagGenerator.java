package no.soprasteria.vtp.util.testdata.person;

import java.time.LocalDate;
import java.util.Random;

public class FødselsdagGenerator {

    private FødselsdagGenerator() {
    }

    private static final Random RANDOM = new Random();

    public static LocalDate genererEnGyldigRandomFødselsdag() {
        var startRange = LocalDate.of(1960, 1, 1);
        var endRange = LocalDate.of(2000, 1, 1);

        var startEpoch = startRange.toEpochDay();
        var endEpoch = endRange.toEpochDay();

        var randomEpochDay = startEpoch + (long) (RANDOM.nextDouble() * endEpoch - startEpoch);

        return LocalDate.ofEpochDay(randomEpochDay);
    }

}
