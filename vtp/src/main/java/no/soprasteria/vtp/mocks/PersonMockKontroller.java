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


/**
 *
 * For at PersonMOckKontrolleren skal være registert på serveren så måm den annoteres med @RestController()
 * Vi ønsker å nå denne kontrolleren på 'http://localhost:8060/api/person' og gjør dette med å spesifisere
 * stien etter http://localhost:8060/api i en @RequestMapping(PersonMockKontroller.PERSON_PATH).
 */


@RestController()
@RequestMapping(PersonMockKontroller.PERSON_PATH)
public class PersonMockKontroller {
    private static final Logger LOG = LoggerFactory.getLogger(PersonMockKontroller.class);
    private static final String FNR_PATH_PARAM = "fnr";
    protected static final String PERSON_PATH = "/person";

    private final Personregister personregister;

    @Autowired
    public PersonMockKontroller(Personregister personregister) {
        this.personregister = personregister;
    }

    /**
     *
     * Lag en GET metode for å hente ut person fra personregisteret. Vi kommer til å kalle dette endepunktet på
     * følgende måte: http://localhost:8060/api/person/22222233333 for å hente informasjon om person med fødselsnummer
     * 22222233333. Endepunktet må ha følgende være:
     *  1) Riktig path (/person/{fnr})
     *  2) Vi skal hente ut fødselsnummeret slik at vi kan slå opp i personregisteret
     *  3) Metoden returnerer objektet Person som allerede er definert.
     *  4) Hvis personen ikke finnes i registeret i VTP, returner null.
     *
     * @param fnr er fødselsnummeret som er oppgitt i requesten.
     * @return Person hvis person finnes, ellers null
     *
     *  Sitter du fast kan du hente inspirasjon fra
     *  @see KjøretøyMockKontroller eller løsningsforslaget i filen PersonMockKontrollerLøsningsforslag.txt i samme mappe
     */

}
