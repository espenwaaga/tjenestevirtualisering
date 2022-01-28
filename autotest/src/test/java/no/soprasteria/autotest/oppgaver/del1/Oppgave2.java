package no.soprasteria.autotest.oppgaver.del1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import no.soprasteria.autotest.generator.ForbipasseringGenerator;
import no.soprasteria.autotest.klienter.BomsystemKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;

class Oppgave2 {
    private static final Logger LOG = LoggerFactory.getLogger(BomsystemKlient.class);
    private static final String FORVENTET_EXCEPTION_MESSAGE = "SV12345 finnes ikke i kjøretøyregisteret!";
    private static final HttpStatus FORVENTET_EXCEPTION_STATUS = HttpStatus.NOT_FOUND;

    private static final BomsystemKlient bomsystemKlient = new BomsystemKlient();

    @Test
    void sendInnForbipasseringerTilBomsystem() {
        var randomRegistereringsnummer = new Registreringsnummer("SV12345");
        var forbipassering = ForbipasseringGenerator.lagForbipassering(randomRegistereringsnummer);

        // TODO: Kanskje litt vanskelig?
        var exception = assertThrows(ResponseStatusException.class,
                () -> bomsystemKlient.registererKjøretøy(forbipassering));
        assertThat(exception.getMessage()).contains(FORVENTET_EXCEPTION_MESSAGE);
        assertThat(exception.getStatus()).isEqualTo(FORVENTET_EXCEPTION_STATUS);

        LOG.info("Bra jobba! Nå er vi et steg nærmere!");
        LOG.warn("Nå feiler registerering av forbipassering med \"{}: {}\"", FORVENTET_EXCEPTION_STATUS.value(), FORVENTET_EXCEPTION_MESSAGE);
        LOG.warn("Vi skal løse dette i Oppgave 3!");
    }

}
