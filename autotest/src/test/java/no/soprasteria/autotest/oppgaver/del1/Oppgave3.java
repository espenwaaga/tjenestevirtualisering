package no.soprasteria.autotest.oppgaver.del1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

import no.soprasteria.autotest.generator.ForbipasseringGenerator;
import no.soprasteria.autotest.klienter.BomsystemKlient;
import no.soprasteria.autotest.klienter.VtpKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;

class Oppgave3 {

    private static final BomsystemKlient bomsystemKlient = new BomsystemKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();

    @Test
    void sendInnForbipssdasseringerTilBomsystem() {
        // Bruk VTP klienten til å opprette et kjøretøy i VTP. Bruk dette registrerningsnummeret ved innsending.
        //      HINT: bruk vtpKlient.lagTestData()

        Registreringsnummer registreringsnummer = null;
        var forbipassering = ForbipasseringGenerator.lagForbipassering(registreringsnummer);
        var forbipasseringRegistrert = bomsystemKlient.registererKjøretøy(forbipassering);
        assertThat(forbipasseringRegistrert).isTrue();
    }
}
