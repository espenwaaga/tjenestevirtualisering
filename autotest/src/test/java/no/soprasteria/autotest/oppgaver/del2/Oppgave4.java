package no.soprasteria.autotest.oppgaver.del2;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import no.soprasteria.autotest.generator.ForbipasseringGenerator;
import no.soprasteria.autotest.klienter.BomsystemKlient;
import no.soprasteria.autotest.klienter.VtpKlient;

class Oppgave4 {

    private static final BomsystemKlient bomsystemKlient = new BomsystemKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();

    @Test
    void sendInnForbipssdasseringerTilBomsystem() {
        var testdata = vtpKlient.lagTestdata(1);
        var registreringsnummer = testdata.kjøretøy().get(0).registreringsnummer();
        var forbipassering = ForbipasseringGenerator.lagForbipassering(registreringsnummer);

        var exception = assertThrows(ResponseStatusException.class,
                () -> bomsystemKlient.registererKjøretøy(forbipassering));
        assertThat(exception.getMessage()).contains("java.net.UnknownHostException: skatteetaten: nodename nor servname provided, or not known");
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
