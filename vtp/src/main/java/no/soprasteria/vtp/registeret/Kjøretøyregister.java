package no.soprasteria.vtp.registeret;

import no.soprasteria.felles.kontrakter.vtp.KjøretøyInfo;
import no.soprasteria.felles.kontrakter.vtp.Registreringsnummer;

import java.util.HashMap;
import java.util.Map;

public class Kjøretøyregister {
    private final Map<Registreringsnummer, KjøretøyInfo> register = new HashMap<>();

    public void add(Registreringsnummer registreringsnummer, KjøretøyInfo kjøretøyInfo) {
        if (register.containsKey(registreringsnummer)){
            return;
        }
        register.put(registreringsnummer, kjøretøyInfo);
    }

    @Override
    public String toString() {
        return "Kjøretøyregister{" +
                "register=" + register +
                '}';
    }

    public KjøretøyInfo get(Registreringsnummer registreringsnummer) {
        return register.get(registreringsnummer);
    }
}
