package no.soprasteria.autotest.oppgaver.del1;

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


    /**
     * Bruk VTP klienten til å opprette et kjøretøy i VTP. Du vil deretter motta Testdata fra VTP.
     * Fra testdataen, hent ut kjøretøy og registreringsnummeret forbunnet med dette kjøretøyet.
     * Bruk dette registreringsnummeret ved oppretting av bompassering.
     *      HINT: vtpTestdataKlient.opprettTestperson()
     */
    @Test
    void sendInnForbipssdasseringerTilBomsystem() {
        // Generer testdata og bruk dette når du lager bompasseringer. Erstatt registreringsnummeret
        // (som nå er satt til null) med registreringsnummrert fra testpersonens kjøretøy.

        Registreringsnummer registreringsnummer = null; // Sett dette til et gyldig registreringsnummer
        var bompassering = BompasseringGenerator.lagBompassering(registreringsnummer);
        var bompasseringRegistrert = bomregistreringsKlient.sendInnPassering(bompassering);
        assertThat(bompasseringRegistrert).isTrue();
    }
}
