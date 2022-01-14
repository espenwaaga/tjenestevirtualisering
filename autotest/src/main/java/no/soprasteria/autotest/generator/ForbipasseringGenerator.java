package no.soprasteria.autotest.generator;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipassering;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.ForbipasseringList;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipasseringsinformasjon;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Sone;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Kjøretøy;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ForbipasseringGenerator {
    private static final Random RANDOM = new Random();


    public static ForbipasseringList lagForbipassering(List<Kjøretøy> kjøretøyList) {
        return new ForbipasseringList(kjøretøyList.stream()
                .map(kjøretøy -> lagForbipassering(map(kjøretøy)))
                .collect(Collectors.toList()));
    }
    public static Forbipassering lagForbipassering(Registreringsnummer registreringsnummer,
                                                   Sone sone) {
        return new Forbipassering(registreringsnummer, lagForbipasseringInformasjon(sone));
    }

    public static Forbipassering lagForbipassering(Registreringsnummer registreringsnummer) {
        return new Forbipassering(
                registreringsnummer,
                lagForbipasseringInformasjon(
                        Sone.random()));
    }

    private static Forbipasseringsinformasjon lagForbipasseringInformasjon(Sone sone) {
        return new Forbipasseringsinformasjon(randomTidspunkt(),
                sone);
    }

    private static LocalDate randomTidspunkt() {
        long minDay = LocalDate.now().minusMonths(1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = RANDOM.nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private static List<Registreringsnummer> mapKjøretøyList(List<Kjøretøy> kjøretøyList) {
        return kjøretøyList.stream()
                .map(ForbipasseringGenerator::map)
                .collect(Collectors.toList());
    }

    private static Registreringsnummer map(Kjøretøy kjøretøy) {
        return kjøretøy.registreringsnummer();
    }
}
