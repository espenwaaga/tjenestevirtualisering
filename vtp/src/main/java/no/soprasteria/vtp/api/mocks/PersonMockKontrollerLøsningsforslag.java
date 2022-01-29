package no.soprasteria.vtp.api.mocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.person.Person;
import no.soprasteria.vtp.config.KontrollerKonfig;
import no.soprasteria.vtp.register.Personregister;

@RestController()
@RequestMapping(PersonMockKontrollerLøsningsforslag.PERSON_PATH)
public class PersonMockKontrollerLøsningsforslag {
    public static final String PERSON_PATH = "/person";
    private static final Logger LOG = LoggerFactory.getLogger(PersonMockKontrollerLøsningsforslag.class);

    private final Personregister personregister;
    private final KontrollerKonfig kontrolleKonfig;

    @Autowired
    public PersonMockKontrollerLøsningsforslag(Personregister personregister, KontrollerKonfig kontrolleKonfig) {
        this.personregister = personregister;
        this.kontrolleKonfig = kontrolleKonfig;
    }

    @GetMapping(value = "/{fnr}")
    public Person hentPersonInfo(@PathVariable("fnr") Fødselsnummer fnr) {
        LOG.info("Henter informasjon om borger med fødselsnummer [{}]", fnr);
        var person = personregister.get(fnr);
        if (person == null) {
            LOG.warn("Person med fødselsnummer {} finnes ikke i registeret til skattetaten!", fnr);
            return null;
        }
        LOG.info("Returnere informasjon om person");
        return person;
    }
}
/***/
