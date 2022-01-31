package no.soprasteria.autotest.klienter.bomsystemet;


import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.krav.Krav;

public class InnsynKlient extends AbstractJerseyRestKlient {
    private static final Logger LOG = LoggerFactory.getLogger(InnsynKlient.class);
    private static final String INNSYN_BASE_URL = "http://localhost:8080";
    private static final String INNSYN_KRAV_PATH = "/api/innsyn/krav";

    public List<Krav> hentAlleKravPåPerson(Fødselsnummer fnr) {
        LOG.info("Henter alle krav som er registrert på person {}", fnr);
        return Optional.ofNullable(client.target(INNSYN_BASE_URL)
                        .path(INNSYN_KRAV_PATH + "/" + fnr.value())
                        .request()
                        .get()
                        .readEntity(new GenericType<List<Krav>>() {}))
                .orElse(List.of());
    }
}
