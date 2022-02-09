package no.soprasteria.autotest.klienter.vtp;

import javax.ws.rs.client.Entity;

import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.vtp.Testpersoner;
import no.soprasteria.felles.kontrakter.vtp.Testperson;

public class VtpTestdataKlient extends AbstractJerseyRestKlient {
    private static final String VTP_BASE_URI = "http://localhost:8060";
    private static final String TESTPERSON_API_PATH = "/api/testdata";
    private static final String OPPRETT_PERSON_PATH = TESTPERSON_API_PATH + "/person";
    private static final String OPPRETT_PERSONER_PATH = TESTPERSON_API_PATH + "/personer";


    public Testperson opprettTestperson() {
        return opprettTestperson(1);
    }

    public Testperson opprettTestperson(int medAntallRegistrerteKjøretøy) {
        return client.target(VTP_BASE_URI)
                .path(OPPRETT_PERSON_PATH)
                .request()
                .post(Entity.json(medAntallRegistrerteKjøretøy), Testperson.class);
    }

    public Testpersoner opprettTestpersoner(int antallPersoner) {
        return client.target(VTP_BASE_URI)
                .path(OPPRETT_PERSONER_PATH)
                .request()
                .post(Entity.json(antallPersoner), Testpersoner.class);
    }
}
