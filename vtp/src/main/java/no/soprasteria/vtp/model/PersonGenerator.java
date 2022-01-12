package no.soprasteria.vtp.model;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.person.Adresse;
import no.soprasteria.felles.kontrakter.bomsystem.person.Kjønn;
import no.soprasteria.felles.kontrakter.bomsystem.person.Navn;
import no.soprasteria.felles.kontrakter.bomsystem.person.Person;
import no.soprasteria.vtp.model.identer.FiktiveFnr;
import no.soprasteria.vtp.model.util.AdresseGenerator;
import no.soprasteria.vtp.model.util.NavnGenerator;

import java.util.Random;

import static no.soprasteria.felles.kontrakter.bomsystem.person.Kjønn.randomKjonn;

public class PersonGenerator {

    NavnGenerator navnGenerator = new NavnGenerator();
    AdresseGenerator adresseGenerator = new AdresseGenerator();
    FiktiveFnr fiktiveFnr = new FiktiveFnr();

    private static final Random RANDOM = new Random();


    Person lagFiktivPerson() {
        var fiktivPerson = new FiktivPerson();
        fiktivPerson.kjønn = randomKjonn();

        if (fiktivPerson.kjønn == Kjønn.K) {
            fiktivPerson.fornavn = navnGenerator.getRandomFornavnKvinne();
            fiktivPerson.fnr = fiktiveFnr.tilfeldigKvinneFnr();
        }
        else {
            fiktivPerson.fornavn = navnGenerator.getRandomFornavnMann();
            fiktivPerson.fnr = fiktiveFnr.tilfeldigMannFnr();

        }
        fiktivPerson.etternavn = navnGenerator.getRandomEtternavn();
        fiktivPerson.adresse = adresseGenerator.getRandomAdresse();

        return new Person(
                mapNavn(fiktivPerson),
                new Fødselsnummer(fiktivPerson.fnr),
                fiktivPerson.adresse,
                "test@test.no"
        );

    }

    private Navn mapNavn(FiktivPerson fiktivPerson) {
        return new Navn(fiktivPerson.fornavn, fiktivPerson.etternavn);
    }


    public class FiktivPerson {
        String fornavn;
        String etternavn;
        String fnr;
        Kjønn kjønn;
        Adresse adresse;

    }


}
