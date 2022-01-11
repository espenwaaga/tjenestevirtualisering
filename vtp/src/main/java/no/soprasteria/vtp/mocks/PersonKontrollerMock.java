package no.soprasteria.vtp.mocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.person.PersonInformasjon;
import no.soprasteria.vtp.register.Personregister;

@RestController()
@RequestMapping(PersonKontrollerMock.PERSON_PATH)
public class PersonKontrollerMock {
    public static final String PERSON_PATH = "/person";
    private static final Logger LOG = LoggerFactory.getLogger(PersonKontrollerMock.class);

    private final Personregister personregister;

    @Autowired
    public PersonKontrollerMock(Personregister personregister) {
        this.personregister = personregister;
    }

    @GetMapping(value = "/{fnr}")
    public PersonInformasjon hentKjøretøyInfo(@PathVariable("fnr") Fødselsnummer fnr) {
        LOG.info("Henter informasjon om borger [{}]", fnr.value());
        var personinfo = personregister.get(fnr);
        if (personinfo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person finnes ikke i registeret!");
        }
        return personinfo;
    }
}
