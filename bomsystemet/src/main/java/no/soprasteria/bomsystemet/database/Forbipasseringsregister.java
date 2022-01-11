package no.soprasteria.bomsystemet.database;

import static java.util.Collections.emptyList;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipasseringsinformasjon;

/**
 * Dette er et register som simulere en database hvor vi lagrer forbipasseringer.
 * For enkelhet skyld lagrer vi dette in memory og persistere ikke dette på tvers av kjøringer.
 */
public class Forbipasseringsregister {

    private final Map<Registreringsnummer, List<Forbipasseringsinformasjon>> register = new HashMap<>();

    public void add(Registreringsnummer registreringsnummer, Forbipasseringsinformasjon forbipasseringsinformasjon) {

        if (!register.containsKey(registreringsnummer)) {
            var forbipasseringer = new ArrayList<Forbipasseringsinformasjon>();
            forbipasseringer.add(forbipasseringsinformasjon);
            register.put(registreringsnummer, forbipasseringer);
        } else {
            var eksisterendeForbipasseringer = register.get(registreringsnummer);
            if (isEmpty(eksisterendeForbipasseringer)) {
                eksisterendeForbipasseringer = new ArrayList<>();
            }
            eksisterendeForbipasseringer.add(forbipasseringsinformasjon);
        }
    }

    public List<Forbipasseringsinformasjon> get(Registreringsnummer registreringsnummer) {
        return Optional.ofNullable(register.get(registreringsnummer)).orElse(emptyList());
    }

    public void remove(Registreringsnummer registreringsnummer) {
        register.remove(registreringsnummer);
    }

    @Override
    public String toString() {
        return "Forbipasseringsregister{" +
                "register=" + register +
                '}';
    }
}
