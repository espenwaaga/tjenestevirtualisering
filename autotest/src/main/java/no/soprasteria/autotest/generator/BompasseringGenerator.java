package no.soprasteria.autotest.generator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Bompassering;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Bompasseringsinformasjon;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Sone;

public class BompasseringGenerator {

    private BompasseringGenerator() {
    }

    private static final Random RANDOM = new Random();

    public static Bompassering lagBompassering(Registreringsnummer registreringsnummer, Sone sone) {
        return lagBompassering(registreringsnummer, lagBompasseringInformasjon(sone));
    }

    public static Bompassering lagBompassering(Registreringsnummer registreringsnummer, LocalDateTime tidspunkt) {
        return lagBompassering(registreringsnummer, lagBompasseringInformasjon(tidspunkt, randomSone()));
    }

    public static Bompassering lagBompassering(Registreringsnummer registreringsnummer) {
        return lagBompassering(registreringsnummer, lagBompasseringInformasjon(randomSone()));
    }

    private static Bompassering lagBompassering(Registreringsnummer registreringsnummer, Bompasseringsinformasjon bompasseringsinformasjon) {
        if (registreringsnummer == null) {
            throw new RuntimeException("Registreringsnummer kan ikke være null!");
        }
        return new Bompassering(registreringsnummer, bompasseringsinformasjon);
    }

    private static Bompasseringsinformasjon lagBompasseringInformasjon(Sone sone) {
        return lagBompasseringInformasjon(randomTidspunkt(), sone);
    }

    private static Bompasseringsinformasjon lagBompasseringInformasjon(LocalDateTime tidspunkt, Sone sone) {
        return new Bompasseringsinformasjon(tidspunkt, sone);
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

    private static Sone randomSone() {
        var alleSonene = List.of(Sone.values());
        return alleSonene.get(RANDOM.nextInt(alleSonene.size()));
    }
}
