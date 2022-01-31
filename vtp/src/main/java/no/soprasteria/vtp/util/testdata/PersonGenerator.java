package no.soprasteria.vtp.util.testdata;

import no.soprasteria.felles.kontrakter.bomsystem.person.Navn;
import no.soprasteria.felles.kontrakter.bomsystem.person.Person;
import no.soprasteria.vtp.util.testdata.person.FnrGenerator;
import no.soprasteria.vtp.util.testdata.person.AdresseGenerator;
import no.soprasteria.vtp.util.testdata.person.NavnGenerator;

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
