package no.soprasteria.autotest.løsningsforslag.del1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.autotest.generator.BompasseringGenerator;
import no.soprasteria.autotest.klienter.bomsystemet.BomregistreringsKlient;
import no.soprasteria.autotest.klienter.vtp.VtpKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;

class Oppgave3 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);

    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();


    /**
     * Bruk VTP klienten til å opprette et kjøretøy i VTP.
     * Fra testdataen, hent kjøretøy og bruk dette registreringsnummeret ved innsenidng.
     *      HINT: vtpKlient.opprettTestperson()
     */
    @Test
    void sendInnForbipssdasseringerTilBomsystem() {
        // Generer testdata og bruk dette når du lager bompasseringer.
        var testperson = vtpKlient.opprettTestperson();
        Registreringsnummer registreringsnummer = testperson.kjøretøy().registreringsnummer();
        var bompassering = BompasseringGenerator.lagBompassering(registreringsnummer);
        var bompasseringErRegistrert = bomregistreringsKlient.sendInnPassering(bompassering);
        assertThat(bompasseringErRegistrert).isTrue();

        // legg til verifisering at ...
    }
}
