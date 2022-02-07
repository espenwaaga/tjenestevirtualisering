package no.soprasteria.autotest.oppgaver.del1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.ws.rs.ProcessingException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import no.soprasteria.autotest.generator.BompasseringGenerator;
import no.soprasteria.autotest.klienter.bomsystemet.BomregistreringsKlient;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Bompassering;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;

class Oppgave1 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);
    private static final String FORVENTET_EXCEPTION_MESSAGE = "Connect to localhost:8060 [localhost/127.0.0.1, localhost/0:0:0:0:0:0:0:1] failed";
    private static final HttpStatus FORVENTET_EXCEPTION_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();

    @Test
    void sendInnBompasseringerTilBomsystem() {
        var randomRegistereringsnummer = new Registreringsnummer("SV12345");
        var bompassering = BompasseringGenerator.lagBompassering(randomRegistereringsnummer);

        // Verifiserer at kallet hiver en forventet exception
        var exception = assertThrows(ResponseStatusException.class,
                () -> bomregistreringsKlient.sendInnPassering(bompassering));
        assertThat(exception.getMessage())
                .as("Feilmelding til Exception")
                .contains(FORVENTET_EXCEPTION_MESSAGE);
        assertThat(exception.getStatus())
                .as("Status kode til Exception")
                .isEqualTo(FORVENTET_EXCEPTION_STATUS);

        LOG.info("Bra jobba! Nå er vi et steg nærmere!");
        LOG.warn("Nå feiler registerering av bompassering med \"{}: {}\"", FORVENTET_EXCEPTION_STATUS.value(), FORVENTET_EXCEPTION_MESSAGE);
        LOG.warn("Vi skal løse dette i Oppgave 2!");
    }

}
