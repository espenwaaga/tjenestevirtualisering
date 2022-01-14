package no.soprasteria.autotest.klienter.bomsystemet;

import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipassering;

import javax.ws.rs.client.Entity;

public class InnsendingKlient extends AbstractJerseyRestKlient {

    private static final String CONTEXT_PATH = "/api";
    private static final String BOMSYSTEMET_BASE_URI = "http://localhost:8080";
    private static final String VTP_BASE_URI = "http://localhost:8060";

    private static final String REGISTRER_FORBIPASSERING_PATH = CONTEXT_PATH + "/mottak";

    private static final String VTP_KJØRETØY_PATH = CONTEXT_PATH + "/kjøretøy";
    private static final String VTP_TESTDATA_PATH = CONTEXT_PATH + "/testdata";


    public void registererKjøretøy(Forbipassering forbipassering) {
        client.target(BOMSYSTEMET_BASE_URI)
                .path(REGISTRER_FORBIPASSERING_PATH)
                .request()
                .post(Entity.json(forbipassering));
    }


}
