package no.soprasteria.autotest.generator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipassering;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.ForbipasseringList;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipasseringsinformasjon;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Sone;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Kjøretøy;

public class ForbipasseringGenerator {
    private static final Random RANDOM = new Random();


    public static ForbipasseringList lagForbipassering(List<Kjøretøy> kjøretøyList) {
        return new ForbipasseringList(kjøretøyList.stream()
                .map(kjøretøy -> lagForbipassering(map(kjøretøy)))
                .toList());
    }
    public static Forbipassering lagForbipassering(Registreringsnummer registreringsnummer,
                                                   Sone sone) {
        return new Forbipassering(registreringsnummer, lagForbipasseringInformasjon(sone));
    }

    public static Forbipassering lagForbipassering(Registreringsnummer registreringsnummer, LocalDateTime tidspunkt) {
        return new Forbipassering(
                registreringsnummer,
                lagForbipasseringInformasjon(tidspunkt, Sone.random()));
    }

    private static Forbipasseringsinformasjon lagForbipasseringInformasjon(LocalDateTime tidspunkt, Sone sone) {
        return new Forbipasseringsinformasjon(tidspunkt, sone);
    }

    public static Forbipassering lagForbipassering(Registreringsnummer registreringsnummer) {
        if (registreringsnummer == null) {
            throw new RuntimeException("Kan ikke lage forbipasseringsinformasjon uten et registreringsnummer!");
        }
        return new Forbipassering(
                registreringsnummer,
                lagForbipasseringInformasjon(Sone.random()));
    }

    private static Forbipasseringsinformasjon lagForbipasseringInformasjon(Sone sone) {
        return new Forbipasseringsinformasjon(randomTidspunkt(), sone);
    }


    private static LocalDateTime randomTidspunkt() {
        var randomDate = randomDato();
        return LocalDateTime.of(randomDate, LocalTime.now());
    }

    private static LocalDate randomDato() {
        var minDay = LocalDate.now().minusMonths(1).toEpochDay();
        var maxDay = LocalDate.now().toEpochDay();
        var randomDay = RANDOM.nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private static List<Registreringsnummer> mapKjøretøyList(List<Kjøretøy> kjøretøyList) {
        return kjøretøyList.stream()
                .map(ForbipasseringGenerator::map)
                .toList();
    }

    private static Registreringsnummer map(Kjøretøy kjøretøy) {
        return kjøretøy.registreringsnummer();
    }
}
