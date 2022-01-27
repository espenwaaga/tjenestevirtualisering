package no.soprasteria.autotest;

import static no.soprasteria.autotest.generator.ForbipasseringGenerator.lagForbipassering;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import no.soprasteria.autotest.klienter.BomsystemKlient;
import no.soprasteria.autotest.klienter.VtpKlient;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;

class KjøretøyregistreringTest {

    private VtpKlient vtpKlient = new VtpKlient();
    private final BomsystemKlient bomsystemKlient = new BomsystemKlient();

    @Test
    void testKjøretøyregisterring() {
        var testdata = vtpKlient.lagKjøretøy(KjøretøyKlasse.KLASSE1);

        var kjøretøy = testdata.kjøretøy().get(0);
        var forbipassering1 = lagForbipassering(kjøretøy.registreringsnummer(), LocalDateTime.now().minusHours(3));
        var forbipassering2 = lagForbipassering(kjøretøy.registreringsnummer(), LocalDateTime.now().minusHours(1).minusMinutes(30));
        var forbipassering3 = lagForbipassering(kjøretøy.registreringsnummer(), LocalDateTime.now().minusHours(1).minusMinutes(10));
        var forbipassering4 = lagForbipassering(kjøretøy.registreringsnummer(), LocalDateTime.now().minusHours(1));

        bomsystemKlient.registererKjøretøy(forbipassering1);
        bomsystemKlient.registererKjøretøy(forbipassering2);
        bomsystemKlient.registererKjøretøy(forbipassering3);
        bomsystemKlient.registererKjøretøy(forbipassering4);

        var krav = bomsystemKlient.hentAlleKravPåPerson(kjøretøy.kjøretøyInfo().eier().fnr());
        assertThat(krav.size()).isEqualTo(2);

    }
}
