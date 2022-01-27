package no.soprasteria.bomsystemet.oppslag.kjøretøy;

import java.net.URI;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;

@ConfigurationProperties(prefix = "oppslag.kjoretoy")
public class KjøretøyOppslagKlient extends AbstractJerseyRestKlient {

    private static final Logger LOG = LoggerFactory.getLogger(KjøretøyOppslagKlient.class);
    private static final String DEFAULT_BASE_URL = "http://veivesenet";
    private static final String DEFAULT_KJØRETØY_OPPSLAG_PATH = "/api/kjøretøy";

    private final URI baseUrl;
    private final String oppslagPath;

    @ConstructorBinding
    public KjøretøyOppslagKlient(@DefaultValue(DEFAULT_BASE_URL) URI baseUrl,
                                 @DefaultValue(DEFAULT_KJØRETØY_OPPSLAG_PATH) String oppslagPath) {
        this.baseUrl = baseUrl;
        this.oppslagPath = oppslagPath;
    }

    public KjøretøyInfo hentInformasjonOmKjøretøy(Registreringsnummer registreringsnummer) {
        LOG.info("Henter informasjon om kjøretøy med registreringsnummer {}", registreringsnummer);
        var kjøretøyInfo =  client.target(baseUrl)
                .path(getOppslagPath(registreringsnummer))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(KjøretøyInfo.class);
        if (kjøretøyInfo == null) {
            LOG.warn("Fant ikke kjøretøy i registeret!");
        }
        return kjøretøyInfo;
    }

    public boolean finnesKjøretøyIRegister(Registreringsnummer registreringsnummer) {
        var kjøretøyinfo =  hentInformasjonOmKjøretøy(registreringsnummer);
        if (kjøretøyinfo == null) {
            return false;
        }
        return true;
    }


    private String getOppslagPath(Registreringsnummer registreringsnummer) {
        return oppslagPath + "/" + registreringsnummer.value();
    }
}
