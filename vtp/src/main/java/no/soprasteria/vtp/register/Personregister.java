package no.soprasteria.vtp.register;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.person.Person;
import no.soprasteria.vtp.api.TestdataKontroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Personregister {
    private final Map<Fødselsnummer, Person> register = new HashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(TestdataKontroller.class);


    public void add(Fødselsnummer fødselsnummer, Person personInformasjon) {
        if (register.containsKey(fødselsnummer)){
            register.replace(fødselsnummer, personInformasjon);
            return;
        }
        register.put(fødselsnummer, personInformasjon);
    }

    public void add(Person person) {
        LOG.info("Legger til person med FNR: {}", person.fnr());
        add(person.fnr(), person);
    }

    public void add(List<Person> personInformasjon) {
        personInformasjon.forEach(this::add);
    }

    public Person get(Fødselsnummer fødselsnummer) {
        return register.get(fødselsnummer);
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
