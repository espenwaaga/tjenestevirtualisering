package no.soprasteria.autotest.oppgaver.del1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import no.soprasteria.autotest.generator.ForbipasseringGenerator;
import no.soprasteria.autotest.klienter.BomsystemKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;

class Oppgave1 {

    private static final BomsystemKlient bomsystemKlient = new BomsystemKlient();

    @Test
    void sendInnForbipasseringerTilBomsystem() {
        var randomRegistereringsnummer = new Registreringsnummer("SV24523");
        var forbipassering = ForbipasseringGenerator.lagForbipassering(randomRegistereringsnummer);

        // TODO: Kanskje litt vanskelig?
        var exception = assertThrows(ResponseStatusException.class,
                () -> bomsystemKlient.registererKjøretøy(forbipassering));
        assertThat(exception.getMessage()).contains("java.net.UnknownHostException: veivesenet: nodename nor servname provided, or not known");
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
