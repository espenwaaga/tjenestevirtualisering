package no.soprasteria.vtp.testdataGenerator;

import no.soprasteria.felles.kontrakter.bomsystem.person.Navn;
import no.soprasteria.felles.kontrakter.bomsystem.person.Person;
import no.soprasteria.vtp.testdataGenerator.identer.FnrGenerator;
import no.soprasteria.vtp.testdataGenerator.util.AdresseGenerator;
import no.soprasteria.vtp.testdataGenerator.util.NavnGenerator;

import static no.soprasteria.felles.kontrakter.bomsystem.person.Kjønn.randomKjonn;

public class PersonGenerator {

    private PersonGenerator() {
    }

    public static Person lagFiktivPerson() {
        var kjønn = randomKjonn();

        return new Person(
                new Navn(NavnGenerator.getRandomFornavn(kjønn),
                        NavnGenerator.getRandomEtternavn()),
                FnrGenerator.tilfedigFnr(kjønn),
                AdresseGenerator.getRandomAdresse(),
                "test@test.no"
        );
    }
}
