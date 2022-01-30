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

    public static Forbipassering lagForbipassering(Registreringsnummer registreringsnummer, Sone sone) {
        return lagForbipassering(registreringsnummer, lagForbipasseringInformasjon(sone));
    }

    public static Forbipassering lagForbipassering(Registreringsnummer registreringsnummer, LocalDateTime tidspunkt) {
        return lagForbipassering(registreringsnummer, lagForbipasseringInformasjon(tidspunkt, Sone.random()));
    }

    public static Forbipassering lagForbipassering(Registreringsnummer registreringsnummer) {
        return lagForbipassering(registreringsnummer, lagForbipasseringInformasjon(Sone.random()));
    }

    private static Forbipassering lagForbipassering(Registreringsnummer registreringsnummer, Forbipasseringsinformasjon forbipasseringsinformasjon) {
        if (registreringsnummer == null) {
            throw new RuntimeException("Registreringsnummer kan ikke være null!");
        }
        return new Forbipassering(registreringsnummer, forbipasseringsinformasjon);
    }

    private static Forbipasseringsinformasjon lagForbipasseringInformasjon(Sone sone) {
        return lagForbipasseringInformasjon(randomTidspunkt(), sone);
    }

    private static Forbipasseringsinformasjon lagForbipasseringInformasjon(LocalDateTime tidspunkt, Sone sone) {
        return new Forbipasseringsinformasjon(tidspunkt, sone);
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
}
