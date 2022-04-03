package no.soprasteria.autotest.oppgaver.del1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import no.soprasteria.autotest.generator.BompasseringGenerator;
import no.soprasteria.autotest.klienter.bomsystemet.BomregistreringsKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;

class Oppgave2 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);
    private static final String FORVENTET_EXCEPTION_MESSAGE = "SV12345 finnes ikke i kjøretøyregisteret!";
    private static final HttpStatus FORVENTET_EXCEPTION_STATUS = HttpStatus.NOT_FOUND;

    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();

    @Test
    void sendInnBompasseringerTilBomsystem() throws Exception {
        var randomRegistereringsnummer = new Registreringsnummer("SV12345");
        var bompassering = BompasseringGenerator.lagBompassering(randomRegistereringsnummer);

        // Verifiserer at kallet hive en forventet exception
        var exception = assertThrows(Exception.class,
            () -> bomregistreringsKlient.sendInnPassering(bompassering));
        if (!exception.getMessage().contains(FORVENTET_EXCEPTION_MESSAGE))  {
            throw exception;
        }

        LOG.info("Bra jobba! Nå er vi et steg nærmere!");
        LOG.warn("Nå feiler registerering av bompassering med \"{}: {}\"", FORVENTET_EXCEPTION_STATUS, FORVENTET_EXCEPTION_MESSAGE);
        LOG.warn("Vi skal løse dette i Oppgave 3!");
    }

}
