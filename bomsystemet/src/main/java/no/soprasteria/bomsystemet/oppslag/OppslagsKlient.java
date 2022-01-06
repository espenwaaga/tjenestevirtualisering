package no.soprasteria.bomsystemet.oppslag;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.vtp.Registreringsnummer;

public class OppslagsKlient extends AbstractJerseyRestKlient {

    private static final String DEFAULT_BASE_URL = "http://veivesenet";
    private static final String OPPSLAG_PATH = "/api/person";

    public void hentOpplysninger(Registreringsnummer registreringsnummer) {
        client.target(DEFAULT_BASE_URL)
                .path(OPPSLAG_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(registreringsnummer));
    }
}
