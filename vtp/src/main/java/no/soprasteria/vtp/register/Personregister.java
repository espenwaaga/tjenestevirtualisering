package no.soprasteria.vtp.register;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.person.Person;

import java.util.HashMap;
import java.util.Map;

public class Personregister {
    private final Map<Fødselsnummer, Person> register = new HashMap<>();

    public void add(Fødselsnummer fødselsnummer, Person personInformasjon) {
        if (register.containsKey(fødselsnummer)){
            register.replace(fødselsnummer, personInformasjon);
            return;
        }
        register.put(fødselsnummer, personInformasjon);
    }

    public void add(Person personInformasjon) {
        add(personInformasjon.fnr(), personInformasjon);
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
