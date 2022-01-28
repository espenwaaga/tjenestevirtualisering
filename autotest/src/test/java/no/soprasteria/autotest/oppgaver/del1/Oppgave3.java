package no.soprasteria.autotest.oppgaver.del1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.autotest.generator.ForbipasseringGenerator;
import no.soprasteria.autotest.klienter.BomsystemKlient;
import no.soprasteria.autotest.klienter.VtpKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;

class Oppgave3 {
    private static final Logger LOG = LoggerFactory.getLogger(BomsystemKlient.class);

    private static final BomsystemKlient bomsystemKlient = new BomsystemKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();


    /**
     * Bruk VTP klienten til å opprette et kjøretøy i VTP.
     * Fra testdataen, hent kjøretøy og bruk dette registreringsnummeret ved innsenidng.
     *      HINT: vtpKlient.lagTestData(1)
     */
    @Test
    void sendInnForbipssdasseringerTilBomsystem() {
        // Generer testdata og bruk dette når du lager forbipasseringer.

        Registreringsnummer registreringsnummer = null; // Sett dette til et gyldig registreringsnummer
        var forbipassering = ForbipasseringGenerator.lagForbipassering(registreringsnummer);
        var forbipasseringRegistrert = bomsystemKlient.registererKjøretøy(forbipassering);
        assertThat(forbipasseringRegistrert).isTrue();
    }
}
