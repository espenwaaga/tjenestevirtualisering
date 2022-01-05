package no.soprasteria.autotest.registrering;

import no.soprasteria.autotest.klienter.bomsystemet.InnsendingKlient;
import no.soprasteria.felles.kontrakter.vtp.Kjøretøy;
import no.soprasteria.felles.kontrakter.vtp.KjøretøyInfo;
import no.soprasteria.felles.kontrakter.vtp.KjøretøyKlasse;
import no.soprasteria.felles.kontrakter.vtp.Registreringsnummer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VtpTest {

    private InnsendingKlient innsendingKlient = new InnsendingKlient();

    @Test
    public void lagKjøretøyTest() {
        var kjøretøy = new Kjøretøy(new Registreringsnummer("DR28292"),
                new KjøretøyInfo(KjøretøyKlasse.KLASSE1));

        innsendingKlient.lagKjøretøy(kjøretøy);

        var info = innsendingKlient.hentKjøretøyInfo(kjøretøy.registreringsnummer());
        assertEquals(kjøretøy.kjøretøyInfo(), info);
    }
}
