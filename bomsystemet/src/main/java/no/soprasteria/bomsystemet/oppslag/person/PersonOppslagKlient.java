package no.soprasteria.bomsystemet.oppslag.person;

import javax.ws.rs.core.MediaType;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.person.PersonInformasjon;

@Component
@ConfigurationProperties(prefix = "oppslag.person") // TODO: Hvorfor fungere ikke dette?
public class PersonOppslagKlient extends AbstractJerseyRestKlient {

    private static final String DEFAULT_BASE_URL = "http://skatteetaten";
    private static final String OPPSLAG_PATH = "/api/person";

    public PersonInformasjon hentOpplysninger(Fødselsnummer fødselsnummer) {
        return client.target(DEFAULT_BASE_URL)
                .path(OPPSLAG_PATH + "/" + fødselsnummer.value())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(PersonInformasjon.class);
    }
}
