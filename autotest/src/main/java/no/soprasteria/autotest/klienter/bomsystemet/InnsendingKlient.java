package no.soprasteria.autotest.klienter.bomsystemet;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.bomsystem.Forbipassering;

public class InnsendingKlient extends AbstractJerseyRestKlient {

    private static final String BOMSYSTEMET_BASE_URI = "http://localhost:8080";
    private static final String BOMSYSTEMET_CONTEXT_PATH = "/api";

    private static final String REGISTRER_FORBIPASSERING_PATH = BOMSYSTEMET_CONTEXT_PATH + "/mottak";

    public void registererKjøretøy(Forbipassering forbipassering) {
        client.target(BOMSYSTEMET_BASE_URI)
                .path(REGISTRER_FORBIPASSERING_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(forbipassering));
    }
}
