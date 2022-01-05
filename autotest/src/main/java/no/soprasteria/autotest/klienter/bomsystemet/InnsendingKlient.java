package no.soprasteria.autotest.klienter.bomsystemet;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.bomsystem.Forbipassering;
import no.soprasteria.felles.kontrakter.vtp.Kjøretøy;
import no.soprasteria.felles.kontrakter.vtp.KjøretøyInfo;
import no.soprasteria.felles.kontrakter.vtp.Registreringsnummer;

public class InnsendingKlient extends AbstractJerseyRestKlient {

    private static final String CONTEXT_PATH = "/api";
    private static final String BOMSYSTEMET_BASE_URI = "http://localhost:8080";
    private static final String VTP_BASE_URI = "http://localhost:8060";

    private static final String REGISTRER_FORBIPASSERING_PATH = CONTEXT_PATH + "/mottak";
    private static final String VTP_KJØRETØY_PATH = CONTEXT_PATH + "/kjøretøy";


    public void registererKjøretøy(Forbipassering forbipassering) {
        client.target(BOMSYSTEMET_BASE_URI)
                .path(REGISTRER_FORBIPASSERING_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(forbipassering));
    }

    public void lagKjøretøy(Kjøretøy kjøretøy) {
        client.target(VTP_BASE_URI)
                .path(VTP_KJØRETØY_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(kjøretøy));
    }

    public KjøretøyInfo hentKjøretøyInfo(Registreringsnummer registreringsnummer) {
        return client.target(VTP_BASE_URI)
                .path(VTP_KJØRETØY_PATH + "/" + registreringsnummer.value())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(KjøretøyInfo.class);

    }
}
