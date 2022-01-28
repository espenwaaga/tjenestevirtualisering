package no.soprasteria.autotest.oppgaver.del2;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import no.soprasteria.autotest.generator.ForbipasseringGenerator;
import no.soprasteria.autotest.klienter.BomsystemKlient;
import no.soprasteria.autotest.klienter.VtpKlient;

class Oppgave5 {
    private static final Logger LOG = LoggerFactory.getLogger(BomsystemKlient.class);
    private static final String FORVENTET_EXCEPTION_MESSAGE = "java.net.UnknownHostException: skatteetaten: nodename nor servname provided, or not known";
    private static final HttpStatus FORVENTET_EXCEPTION_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

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
        assertThat(exception.getStatus()).isEqualTo(FORVENTET_EXCEPTION_STATUS);

        LOG.info("Bra jobba! Nå er vi et steg nærmere!");
        LOG.warn("Nå feiler registerering av forbipassering med \"{}: {}\"", FORVENTET_EXCEPTION_STATUS.value(), FORVENTET_EXCEPTION_MESSAGE);
        LOG.warn("Vi skal løse dette i Oppgave 5!");
    }
}
