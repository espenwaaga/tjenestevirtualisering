package no.soprasteria.bomsystemet.oppslag.person;

import java.net.URI;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.person.PersonInformasjon;

@ConfigurationProperties(prefix = "oppslag.person")
public class PersonOppslagKlient extends AbstractJerseyRestKlient {

    private static final Logger LOG = LoggerFactory.getLogger(PersonOppslagKlient.class);
    private static final String DEFAULT_BASE_URL = "http://skatteetaten";
    private static final String DEFAULT_OPPSLAG_PATH = "/api/person";

    private final URI baseUrl;
    private final String oppslagPath;

    @ConstructorBinding
    public PersonOppslagKlient(@DefaultValue(DEFAULT_BASE_URL) URI baseUrl,
                               @DefaultValue(DEFAULT_OPPSLAG_PATH) String oppslagPath) {
        this.baseUrl = baseUrl;
        this.oppslagPath = oppslagPath;
    }

    public PersonInformasjon hentOpplysninger(Fødselsnummer fnr) {
        LOG.info("Henter personinformasjon om person med fødselsnummer {}", fnr);
        return client.target(baseUrl)
                .path(getOppslagPath(fnr))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(PersonInformasjon.class);
    }

    private String getOppslagPath(Fødselsnummer fødselsnummer) {
        return oppslagPath + "/" + fødselsnummer.value();
    }
}
