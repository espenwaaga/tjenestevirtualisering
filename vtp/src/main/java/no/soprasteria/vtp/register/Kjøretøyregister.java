package no.soprasteria.vtp.register;

import java.util.HashMap;
import java.util.Map;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;

public class Kjøretøyregister {
    private final Map<Registreringsnummer, KjøretøyInfo> register = new HashMap<>();

    public void add(Registreringsnummer registreringsnummer, KjøretøyInfo kjøretøyInfo) {
        if (register.containsKey(registreringsnummer)){
            register.replace(registreringsnummer, kjøretøyInfo);
            return;
        }
        register.put(registreringsnummer, kjøretøyInfo);
    }

    public KjøretøyInfo get(Registreringsnummer registreringsnummer) {
        return register.get(registreringsnummer);
    }

    @Override
    public String toString() {
        return "Kjøretøyregister{" +
                "register=" + register +
                '}';
    }
}
