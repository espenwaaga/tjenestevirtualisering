package no.soprasteria.autotest;

import no.soprasteria.autotest.klienter.bomsystemet.BomsystemKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import org.junit.jupiter.api.Test;

import static no.soprasteria.autotest.generator.ForbipasseringGenerator.lagForbipassering;

class KjøretøyregistreringTest {

    private final BomsystemKlient bomsystemKlient = new BomsystemKlient();

    @Test
    void testKjøretøyregisterring() {
        var forbipassering1 = lagForbipassering(new Registreringsnummer("SV245321"));

        bomsystemKlient.registererKjøretøy(forbipassering1);

    }
}
