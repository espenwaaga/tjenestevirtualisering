package no.soprasteria.bomsystemet.registeret;

import static java.util.Collections.emptyList;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import no.soprasteria.bomsystemet.mottak.Forbipasseringsinformasjon;
import no.soprasteria.bomsystemet.mottak.Registreringsnummer;

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

    @Override
    public String toString() {
        return "Forbipasseringsregister{" +
                "register=" + register +
                '}';
    }
}
