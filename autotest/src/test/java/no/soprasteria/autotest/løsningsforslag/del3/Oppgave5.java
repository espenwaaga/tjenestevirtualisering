package no.soprasteria.autotest.løsningsforslag.del3;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.autotest.generator.ForbipasseringGenerator;
import no.soprasteria.autotest.klienter.bomsystemet.BomregistreringsKlient;
import no.soprasteria.autotest.klienter.bomsystemet.KravKlient;
import no.soprasteria.autotest.klienter.vtp.VtpKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;


/**
 * Legg merke til at ForbipasseringGenerator også har en metode for å spesifisere tidspunkt
 * {@link ForbipasseringGenerator#lagForbipassering(Registreringsnummer, LocalDateTime)}
 * LocalDateTime kan du enkelt spesifiser absolutt tidspunkt slik -> LocalDateTime.of(2022, 02, 14, 2, 2, 2),
 * eller relativt tidspunkt -> LocalDateTime.now().minusHours(1)
 */
class Oppgave5 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);

    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final KravKlient kravKlient = new KravKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();


    /**
     * Oppgave 5: Vi skal nå teste at bilisten blir belastet for begge passeringene sine hvis det har gått over en time.
     *  1) Generer to forbipasseringer for gitt kjøretøy (begge må være tilbake i tid og være over en 1 time forskjell)
     *  2) Send inn disse to forbipasseringene i kronologisk rekkefølge.
     *  3) Verifiser at det blir opprettet to krav – en for hver passering.
     */
    @Test
    void toForbipasseringerMedToTimersMellomromSkalOppretteToKrav() {
        var testperson = vtpKlient.opprettTestperson();
        var registreringsnummer = testperson.kjøretøy().registreringsnummer();

        var forbipassering1 = ForbipasseringGenerator.lagForbipassering(registreringsnummer, LocalDateTime.now().minusHours(2));
        var forbipassering2 = ForbipasseringGenerator.lagForbipassering(registreringsnummer, LocalDateTime.now().minusHours(1));

        bomregistreringsKlient.sendInnPassering(forbipassering1);
        bomregistreringsKlient.sendInnPassering(forbipassering2);

        var krav = kravKlient.hentAlleKravPåPerson(testperson.fnr());
        assertThat(krav).hasSize(2);
        assertThat(krav.get(0).beregningsgrunnlag().forbipasseringer()).hasSize(1);
        assertThat(krav.get(1).beregningsgrunnlag().forbipasseringer()).hasSize(1);
    }
}
