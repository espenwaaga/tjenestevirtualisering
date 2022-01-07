package no.soprasteria.bomsystemet.oppslag.kjøretøy;

import javax.ws.rs.core.MediaType;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;

@Component
@ConfigurationProperties(prefix = "oppslag.kjoretoy") // TODO: Hvorfor fungere ikke dette?
public class KjøretøyOppslagKlient extends AbstractJerseyRestKlient {

    private static final String DEFAULT_BASE_URL = "http://veivesenet";
    private static final String KJØRETØY_OPPSLAG_PATH = "/api/kjøretøy";

    public KjøretøyInfo hentInformasjonOmKjøretøy(Registreringsnummer registreringsnummer) {
        return client.target(DEFAULT_BASE_URL)
                .path(KJØRETØY_OPPSLAG_PATH + "/" + registreringsnummer.value())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(KjøretøyInfo.class);
    }
}
