package no.soprasteria.autotest.klienter.bomsystemet;

import no.soprasteria.felles.http.AbstractJerseyRestKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;
import no.soprasteria.felles.kontrakter.vtp.RegistreringsnummerList;
import no.soprasteria.felles.kontrakter.vtp.Testdata;

import javax.ws.rs.core.MediaType;

public class VtpKlient extends AbstractJerseyRestKlient {
    private static final String CONTEXT_PATH = "/api";
    private static final String VTP_BASE_URI = "http://localhost:8060";


    private static final String VTP_KJØRETØY_PATH = CONTEXT_PATH + "/kjøretøy";
    private static final String VTP_TESTDATA_PATH = CONTEXT_PATH + "/testdata";


    public KjøretøyInfo hentKjøretøyInfo(Registreringsnummer registreringsnummer) {
        return client.target(VTP_BASE_URI)
                .path(VTP_KJØRETØY_PATH + "/" + registreringsnummer.value())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(KjøretøyInfo.class);

    }

    public Testdata lagKjøretøy(KjøretøyKlasse kjøretøyKlasse) {
        return client.target(VTP_BASE_URI)
                .path(VTP_TESTDATA_PATH +
                        "/kjøretøy" +
                        "/" + kjøretøyKlasse)
                .request()
                .get(Testdata.class);
    }

    public Testdata lagTestdata(int antallKjøretøy) {
        return client.target(VTP_BASE_URI)
                .path(VTP_TESTDATA_PATH + "/" + antallKjøretøy)
                .request()
                .get(Testdata.class);
    }

    public RegistreringsnummerList hentKjøretøy() {
        return client.target(VTP_BASE_URI)
                .path(VTP_KJØRETØY_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(RegistreringsnummerList.class);

    }
}
