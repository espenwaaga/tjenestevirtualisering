package no.soprasteria.autotest.løsningsforslag.del1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.autotest.generator.BompasseringGenerator;
import no.soprasteria.autotest.klienter.bomsystemet.BomregistreringsKlient;
import no.soprasteria.autotest.klienter.vtp.VtpTestdataKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;

class Oppgave3 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);
    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final VtpTestdataKlient vtpTestdataKlient = new VtpTestdataKlient();


    @Test
    void sendInnForbipssdasseringerTilBomsystem() {
        var testperson = vtpTestdataKlient.opprettTestperson(); // Opprett testdata
        Registreringsnummer registreringsnummer = testperson.kjøretøy().registreringsnummer(); // Henter ut regnummer på kjøretøyet til testpersonen
        var bompassering = BompasseringGenerator.lagBompassering(registreringsnummer);
        var bompasseringErRegistrert = bomregistreringsKlient.sendInnPassering(bompassering);
        assertThat(bompasseringErRegistrert).isTrue();
    }
}
