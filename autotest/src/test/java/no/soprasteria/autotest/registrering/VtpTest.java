package no.soprasteria.autotest.registrering;

import no.soprasteria.autotest.klienter.bomsystemet.InnsendingKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Eier;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Kjøretøy;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;
import no.soprasteria.felles.kontrakter.vtp.TestdataBestilling;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VtpTest {

    private InnsendingKlient innsendingKlient = new InnsendingKlient();

    @Test
    void lagKjøretøyTest() {
        var kjøretøy = new Kjøretøy(new Registreringsnummer("DR28292"),
                new KjøretøyInfo(new Eier(new Fødselsnummer("22222233333")), KjøretøyKlasse.KLASSE1));

        innsendingKlient.lagKjøretøy(kjøretøy);

        var info = innsendingKlient.hentKjøretøyInfo(kjøretøy.registreringsnummer());
        assertEquals(kjøretøy.kjøretøyInfo(), info);
    }

    @Test
    void lagTestdata() {
        var testdata = new TestdataBestilling();
        testdata.leggTilEnTestpersonBestilling(1);
        testdata.leggTilEnTestpersonBestilling(1);
        testdata.leggTilEnTestpersonBestilling(1);
        testdata.leggTilEnTestpersonBestilling(1);
        testdata.leggTilEnTestpersonBestilling(1);
        testdata.leggTilEnTestpersonBestilling(1);
        testdata.leggTilEnTestpersonBestilling(1);
        testdata.leggTilEnTestpersonBestilling(1);

        innsendingKlient.instansierTestscenario(testdata);
        var bilerIRegister = innsendingKlient.hentKjøretøy();

    }
}
