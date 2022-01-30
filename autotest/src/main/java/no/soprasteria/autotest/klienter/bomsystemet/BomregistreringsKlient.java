package no.soprasteria.autotest.klienter.bomsystemet;


import javax.ws.rs.client.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import no.soprasteria.autotest.error.ApiError;
import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipassering;

public class BomregistreringsKlient extends AbstractJerseyRestKlient {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);
    private static final String BOMREGISTRERING_BASE_URL = "http://localhost:8080";
    private static final String CONTEXT_PATH = "/api";
    private static final String REGISTRER_FORBIPASSERING_PATH = CONTEXT_PATH + "/mottak";

    public boolean sendInnPassering(Forbipassering forbipassering) {
        LOG.info("Sender inn forbipassering med registereringsnummer {} til bomsystemet", forbipassering.registreringsnummer());
        var response = client.target(BOMREGISTRERING_BASE_URL)
                .path(REGISTRER_FORBIPASSERING_PATH)
                .request()
                .post(Entity.json(forbipassering));
        if (response.getStatus() != 200) {
            var apiError = response.readEntity(ApiError.class);
            throw new ResponseStatusException(apiError.status(), apiError.message());
        }
        LOG.info("Registering av {} er vellykket!", forbipassering.registreringsnummer());
        return response.readEntity(Boolean.class);
    }
}
