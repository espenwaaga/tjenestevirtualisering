package no.soprasteria.autotest;

import no.soprasteria.autotest.klienter.vtp.VtpKlient;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
