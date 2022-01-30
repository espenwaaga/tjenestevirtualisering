package no.soprasteria.vtp.testdataGenerator;

import java.util.ArrayList;

import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Kjøretøy;
import no.soprasteria.felles.kontrakter.vtp.Testperson;

public class TestdataGenerator {

    private TestdataGenerator() {
    }

    public static Testperson lagTestperson(int antallKjøretøy) {
        var person = PersonGenerator.lagFiktivPerson();
        var kjøretøyList = new ArrayList<Kjøretøy>();

        for (int i=1; i <= antallKjøretøy; i++) {
            kjøretøyList.add(KjøretøyGenerator.lagFiktivtKjøretøy(person.fnr()));
        }

        return new Testperson(person, kjøretøyList);
    }

}
