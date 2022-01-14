package no.soprasteria.vtp.register;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Kjøretøy;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;
import no.soprasteria.vtp.api.TestdataKontroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kjøretøyregister {
    private final Map<Registreringsnummer, KjøretøyInfo> register = new HashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(TestdataKontroller.class);


    public void add(Registreringsnummer registreringsnummer, KjøretøyInfo kjøretøyInfo) {
        if (register.containsKey(registreringsnummer)){
            register.replace(registreringsnummer, kjøretøyInfo);
            return;
        }
        register.put(registreringsnummer, kjøretøyInfo);
    }

    public void add(Kjøretøy kjøretøy) {
        LOG.info("Legger til kjøretøy med registreringsnummer: {}", kjøretøy.registreringsnummer());
        add(kjøretøy.registreringsnummer(), kjøretøy.kjøretøyInfo());
    }

    public void add(List<Kjøretøy> kjøretøyList) {
        kjøretøyList.forEach(this::add);
    }

    public KjøretøyInfo get(Registreringsnummer registreringsnummer) {
        return register.get(registreringsnummer);
    }

    public List<Registreringsnummer> getAlleRegistreringsnummer() {
        return new ArrayList<>(register.keySet());
    }

    public int getSize() {
        return register.size();
    }

    @Override
    public String toString() {
        return "Kjøretøyregister{" +
                "register=" + register +
                '}';
    }
}
