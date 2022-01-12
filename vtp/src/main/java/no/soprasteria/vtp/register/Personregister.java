package no.soprasteria.vtp.register;

import java.util.HashMap;
import java.util.Map;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.person.Person;

public class Personregister {
    private final Map<Fødselsnummer, Person> register = new HashMap<>();

    public void add(Fødselsnummer fødselsnummer, Person personInformasjon) {
        if (register.containsKey(fødselsnummer)){
            register.replace(fødselsnummer, personInformasjon);
            return;
        }
        register.put(fødselsnummer, personInformasjon);
    }

    public Person get(Fødselsnummer fødselsnummer) {
        return register.get(fødselsnummer);
    }

    @Override
    public String toString() {
        return "Kjøretøyregister{" +
                "register=" + register +
                '}';
    }
}
