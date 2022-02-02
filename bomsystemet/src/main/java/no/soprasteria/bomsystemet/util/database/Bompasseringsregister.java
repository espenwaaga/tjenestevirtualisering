package no.soprasteria.bomsystemet.util.database;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Bompasseringsinformasjon;

/**
 * Dette er et register som simulere en database hvor vi lagrer bompasseringer.
 * For enkelhet skyld lagrer vi dette in memory og persistere ikke dette på tvers av kjøringer.
 */
public class Bompasseringsregister {

    private final Map<Registreringsnummer, List<Bompasseringsinformasjon>> register = new HashMap<>();

    public void add(Registreringsnummer registreringsnummer, Bompasseringsinformasjon bompasseringsinformasjon) {
        if (!register.containsKey(registreringsnummer)) {
            var bompasseringer = new ArrayList<Bompasseringsinformasjon>();
            bompasseringer.add(bompasseringsinformasjon);
            register.put(registreringsnummer, bompasseringer);
        } else {
            var eksisterendeBompasseringer = register.get(registreringsnummer);
            eksisterendeBompasseringer.add(bompasseringsinformasjon);
        }
    }

    public List<Bompasseringsinformasjon> get(Registreringsnummer registreringsnummer) {
        return Optional.ofNullable(register.get(registreringsnummer)).orElse(emptyList());
    }

    public void remove(Registreringsnummer registreringsnummer) {
        register.remove(registreringsnummer);
    }

    @Override
    public String toString() {
        return "Bompasseringsregister{" +
                "register=" + register +
                '}';
    }
}
