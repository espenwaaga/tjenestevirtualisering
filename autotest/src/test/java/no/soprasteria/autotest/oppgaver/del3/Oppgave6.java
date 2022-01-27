package no.soprasteria.autotest.oppgaver.del3;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

import no.soprasteria.autotest.generator.ForbipasseringGenerator;
import no.soprasteria.autotest.klienter.BomsystemKlient;
import no.soprasteria.autotest.klienter.VtpKlient;

class Oppgave6 {

    private static final BomsystemKlient bomsystemKlient = new BomsystemKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();


    // TODO
    @Test
    void sendInnForbipssdasseringerTilBomsystem() {
        var testdata = vtpKlient.lagTestdata(1);
        var registreringsnummer = testdata.kjøretøy().get(0).registreringsnummer();
        var forbipassering = ForbipasseringGenerator.lagForbipassering(registreringsnummer);
        var erForbipasseringRegistrert = bomsystemKlient.registererKjøretøy(forbipassering);
        assertThat(erForbipasseringRegistrert).isTrue();

        /**
         * Utvid testen til å sjekke
         *  - Forbipassering registert
         *  - Opprettet et krav på person
         *  - Person er bare trukket for en passering
         */
    }
}
