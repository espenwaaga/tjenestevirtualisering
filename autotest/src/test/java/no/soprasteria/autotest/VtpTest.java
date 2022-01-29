package no.soprasteria.autotest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.soprasteria.autotest.klienter.vtp.VtpKlient;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;

class VtpTest {

    private VtpKlient vtpKlient = new VtpKlient();

    @Test
    void lagKjøretøyTest() {

        var testdata = vtpKlient.lagKjøretøy(KjøretøyKlasse.KLASSE1);

        var info = vtpKlient.hentKjøretøyInfo(testdata.kjøretøy().get(0).registreringsnummer());
        assertEquals(KjøretøyKlasse.KLASSE1, info.kjøretøyKlasse());
    }

    @Test
    void lagTestdata() {

        var testdata = vtpKlient.lagTestdata(100);
        var bilerIRegister = vtpKlient.hentKjøretøy();

    }
}
