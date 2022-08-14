package no.soprasteria.vtp.mocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.person.Person;
import no.soprasteria.vtp.register.Personregister;


@RestController()
@RequestMapping(PersonMockKontroller.PERSON_PATH)
public class PersonMockKontroller {
    static final String PERSON_PATH = "/person";
    private static final Logger LOG = LoggerFactory.getLogger(PersonMockKontroller.class);

    private final Personregister personregister;

    @Autowired
    public PersonMockKontroller(Personregister personregister) {
        this.personregister = personregister;
    }

    /**
     *  For at PersonMockKontroller skal registreres på serveren så må klassen annoteres med @RestController()
     *  Vi ønsker å nå denne kontrolleren på 'http://localhost:8060/api/person' og gjør dette med å spesifisere
     *  stien etter http://localhost:8060/api i @RequestMapping annotering. Dette har vi allerede gjort.
     *   - Root path til applikasjonen er satt og alle API kan nås på http://localhost:8060/api
     *   - Path til denne kontrolleren er satt til /person
     *   - Som resulterer i at kontrolleren kan da nås på http://localhost:8060/api/person
     *
     *
     *
     *  OPPGAVE: Lag en GET metode for å hente ut person fra personregisteret. Vi kommer til å kalle (GET kall)
     *           dette endepunktet med url http://localhost:8060/api/person/{fnr} hvor fnr er et fødselsnummer på
     *           11 siffer satt av klienten som kaller.
     *
     *           Endepunktet må ha følgende egenskaper:
     *              1) Riktig path (/{fnr})
     *              2) Hent ut fødselsnummeret fra Path parameter (hint @PathParam)
     *              3) Bruke dette fødselsnummeret til å slå opp person i personregisteret
     *              4) Metoden returnerer objektet Person som allerede er definert.
     *
     *
     *  @param fnr er fødselsnummeret som er oppgitt som path param i requesten.
     *  @return Person
     *
     *  Sitter du fast kan du hente inspirasjon fra
     *  @see KjøretøyMockKontroller eller løsningsforslaget i filen PersonMockKontrollerLøsningsforslag.txt i samme mappe
     */

}
