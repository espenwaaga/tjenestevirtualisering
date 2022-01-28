package no.soprasteria.autotest.oppgaver.del2;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

import no.soprasteria.autotest.generator.ForbipasseringGenerator;
import no.soprasteria.autotest.klienter.BomsystemKlient;
import no.soprasteria.autotest.klienter.VtpKlient;

class Oppgave7 {

    private static final BomsystemKlient bomsystemKlient = new BomsystemKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();


    // TODO: Her må vi forklare en god del om hvordan dette gjøres. Tilpass i VTP.
    @Test
    void sendInnForbipssdasseringerTilBomsystem() {
        var testdata = vtpKlient.lagTestdata(1);
        var registreringsnummer = testdata.kjøretøy().get(0).registreringsnummer();
        var forbipassering = ForbipasseringGenerator.lagForbipassering(registreringsnummer);
        var erForbipasseringRegistrert = bomsystemKlient.registererKjøretøy(forbipassering);
        assertThat(erForbipasseringRegistrert).isTrue();
    }
}
