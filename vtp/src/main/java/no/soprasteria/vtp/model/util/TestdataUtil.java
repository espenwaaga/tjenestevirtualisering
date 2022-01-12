package no.soprasteria.vtp.model.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class TestdataUtil {

    private TestdataUtil() {
    }

    private static final Random RANDOM = new Random();

    public static LocalDate generateRandomPlausibleBirtdayParent() {
        var startRange = LocalDate.of(1960, 1, 1);
        var endRange = LocalDate.of(2000, 1, 1);

        var startEpoch = startRange.toEpochDay();
        var endEpoch = endRange.toEpochDay();

        var randomEpochDay = startEpoch + (long) (RANDOM.nextDouble() * endEpoch - startEpoch);

        return LocalDate.ofEpochDay(randomEpochDay);
    }

    public static LocalDate generateBirthdateNowMinusThreeYears() {
        var endRange = LocalDateTime.now().toLocalDate();
        var startRange = endRange.minus(3, ChronoUnit.YEARS);

        var startEpoch = startRange.toEpochDay();
        var endEpoch = endRange.toEpochDay();

        var randomEpochDay = startEpoch + (long) (RANDOM.nextDouble() * (endEpoch - startEpoch));

        return LocalDate.ofEpochDay(randomEpochDay);
    }

    public static LocalDate generateBirthdayYoungerThanSixMonths() {
        //Note: this actually returns younger than five months, but ok since it's also younger than six months...
        var endRange = LocalDateTime.now().toLocalDate();
        var startRange = endRange.minus(5, ChronoUnit.MONTHS);

        var startEpoch = startRange.toEpochDay();
        var endEpoch = endRange.toEpochDay();

        var randomEpochDay = startEpoch + (long) (RANDOM.nextDouble() * (endEpoch - startEpoch));

        return LocalDate.ofEpochDay(randomEpochDay);
    }
}
