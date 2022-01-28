package no.soprasteria.autotest.klienter;


import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import no.soprasteria.autotest.error.ApiError;
import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipassering;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.ForbipasseringList;
import no.soprasteria.felles.kontrakter.bomsystem.krav.Krav;

public class BomsystemKlient extends AbstractJerseyRestKlient {
    private static final Logger LOG = LoggerFactory.getLogger(BomsystemKlient.class);
    private static final String CONTEXT_PATH = "/api";
    private static final String BOMSYSTEMET_BASE_URI = "http://localhost:8080";

    private static final String REGISTRER_FORBIPASSERING_PATH = CONTEXT_PATH + "/mottak";
    private static final String KRAV_PATH = CONTEXT_PATH + "/krav";

    public boolean registererKjøretøy(Forbipassering forbipassering) {
        LOG.info("Sender inn forbipassering med registereringsnummer {} til bomsystemet", forbipassering.registreringsnummer());
        var response = client.target(BOMSYSTEMET_BASE_URI)
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

    public void registererKjøretøy(ForbipasseringList forbipasseringList) {
        client.target(BOMSYSTEMET_BASE_URI)
                .path(REGISTRER_FORBIPASSERING_PATH
                + "/list")
                .request()
                .post(Entity.json(forbipasseringList));
    }

    public List<Krav> hentAlleKravPåPerson(Fødselsnummer fnr) {
        return Optional.ofNullable(client.target(BOMSYSTEMET_BASE_URI)
                        .path(KRAV_PATH + "/" + fnr.value())
                        .request()
                        .get()
                        .readEntity(new GenericType<List<Krav>>() {}))
                .orElse(List.of());
    }

}
