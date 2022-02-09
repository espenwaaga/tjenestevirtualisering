package no.soprasteria.autotest.klienter.vtp;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import no.soprasteria.felles.http.AbstractJerseyRestKlient;

public class VtpSimulerIkkeFunskjonelleKravKlient extends AbstractJerseyRestKlient {
    private static final String VTP_BASE_URI = "http://localhost:8060";
    private static final String SIMULERING_IKKEFUNKSJONELLEKRAV_API_PATH = "/api/teknisk";
    private static final String RESET_TO_DEFAULT_PATH = SIMULERING_IKKEFUNKSJONELLEKRAV_API_PATH + "/reset";

    public boolean leggPåDelay(int delaySekunder) {
        return client.target(VTP_BASE_URI)
                .path(SIMULERING_IKKEFUNKSJONELLEKRAV_API_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(delaySekunder), Boolean.class);

    }

    public boolean reset() {
        return client.target(VTP_BASE_URI)
                .path(RESET_TO_DEFAULT_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.text(""), Boolean.class);
    }
}
