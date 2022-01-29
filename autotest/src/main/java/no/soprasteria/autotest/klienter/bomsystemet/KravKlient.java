package no.soprasteria.autotest.klienter.bomsystemet;


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

public class KravKlient extends AbstractJerseyRestKlient {
    private static final Logger LOG = LoggerFactory.getLogger(KravKlient.class);
    private static final String CONTEXT_PATH = "/api";
    private static final String BOMSYSTEMET_BASE_URI = "http://localhost:8080";

    private static final String KRAV_PATH = CONTEXT_PATH + "/krav";

    public List<Krav> hentAlleKravPåPerson(Fødselsnummer fnr) {
        LOG.info("Henter alle krav som er registrert på person {}", fnr);
        return Optional.ofNullable(client.target(BOMSYSTEMET_BASE_URI)
                        .path(KRAV_PATH + "/" + fnr.value())
                        .request()
                        .get()
                        .readEntity(new GenericType<List<Krav>>() {}))
                .orElse(List.of());
    }
}
