package no.soprasteria.autotest.klienter.bomsystemet;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import no.soprasteria.autotest.error.ApiError;
import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Bompassering;

public class BomregistreringsKlient extends AbstractJerseyRestKlient {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);
    private static final String BOMREGISTRERING_BASE_URL = "http://localhost:8080";
    private static final String REGISTRER_BOMPASSERING_PATH = "/api/mottak";

    public boolean sendInnPassering(Bompassering bompassering) {
        LOG.info("Sender inn bompassering med registereringsnummer {} til bomsystemet", bompassering.registreringsnummer());
        var response = client.target(BOMREGISTRERING_BASE_URL)
                .path(REGISTRER_BOMPASSERING_PATH)
                .request()
                .post(Entity.json(bompassering));
        if (response.getStatus() != 200) {
            var apiError = response.readEntity(ApiError.class);
            throw new ResponseStatusException(apiError.status(), apiError.message());
        }
        LOG.info("Registering av {} er vellykket!", bompassering.registreringsnummer());
        return response.readEntity(Boolean.class);
    }
}
