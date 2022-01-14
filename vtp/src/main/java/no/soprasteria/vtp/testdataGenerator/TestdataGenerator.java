package no.soprasteria.vtp.testdataGenerator;

import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Kjøretøy;
import no.soprasteria.felles.kontrakter.bomsystem.person.Person;
import no.soprasteria.felles.kontrakter.vtp.Testdata;

import java.util.ArrayList;

public class TestdataGenerator {


    public static Testdata lagTestdata(int antallKjøretøy) {
        var personList = new ArrayList<Person>();
        var kjøretøyList = new ArrayList<Kjøretøy>();

        for (int i=1; i <= antallKjøretøy; i++) {
            var person = PersonGenerator.lagFiktivPerson();
            personList.add(person);
            kjøretøyList.add(KjøretøyGenerator.lagFiktivtKjøretøy(person.fnr()));
        }

        return new Testdata(personList, kjøretøyList);
    }
}
