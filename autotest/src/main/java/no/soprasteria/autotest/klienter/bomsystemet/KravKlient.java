package no.soprasteria.autotest.klienter.bomsystemet;


import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.krav.Krav;

public class KravKlient extends AbstractJerseyRestKlient {
    private static final Logger LOG = LoggerFactory.getLogger(KravKlient.class);
    private static final String KRAVBEHANDLING_BASE_URL = "http://localhost:8080";
    private static final String CONTEXT_PATH = "/api";
    private static final String KRAV_PATH = CONTEXT_PATH + "/krav";

    public List<Krav> hentAlleKravPåPerson(Fødselsnummer fnr) {
        LOG.info("Henter alle krav som er registrert på person {}", fnr);
        return Optional.ofNullable(client.target(KRAVBEHANDLING_BASE_URL)
                        .path(KRAV_PATH + "/" + fnr.value())
                        .request()
                        .get()
                        .readEntity(new GenericType<List<Krav>>() {}))
                .orElse(List.of());
    }
}
