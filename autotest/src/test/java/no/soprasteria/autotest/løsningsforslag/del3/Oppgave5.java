package no.soprasteria.autotest.løsningsforslag.del3;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.autotest.generator.BompasseringGenerator;
import no.soprasteria.autotest.klienter.bomsystemet.BomregistreringsKlient;
import no.soprasteria.autotest.klienter.bomsystemet.InnsynKlient;
import no.soprasteria.autotest.klienter.vtp.VtpTestdataKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;


/**
 * Legg merke til at BompasseringGenerator også har en metode for å spesifisere tidspunkt
 * {@link BompasseringGenerator#lagBompassering(Registreringsnummer, LocalDateTime)}
 * LocalDateTime kan du enkelt spesifiser absolutt tidspunkt slik -> LocalDateTime.of(2022, 02, 14, 2, 2, 2),
 * eller relativt tidspunkt -> LocalDateTime.now().minusHours(1)
 */
class Oppgave5 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);

    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final InnsynKlient innsynKlient = new InnsynKlient();
    private static final VtpTestdataKlient vtpTestdataKlient = new VtpTestdataKlient();


    /**
     * Oppgave 5: Vi skal nå teste at bilisten blir belastet for begge passeringene sine hvis det har gått over en time.
     *  1) Generer to bompasseringer for gitt kjøretøy (begge må være tilbake i tid og være over en 1 time forskjell)
     *  2) Send inn disse to bompasseringene i kronologisk rekkefølge.
     *  3) Verifiser at det blir opprettet to krav – en for hver passering.
     */
    @Test
    void toBompasseringerMedToTimersMellomromSkalOppretteToKrav() {
        var testperson = vtpTestdataKlient.opprettTestperson();
        var registreringsnummer = testperson.kjøretøy().registreringsnummer();

        var bompassering1 = BompasseringGenerator.lagBompassering(registreringsnummer, LocalDateTime.now().minusHours(3));
        var bompassering2 = BompasseringGenerator.lagBompassering(registreringsnummer, LocalDateTime.now().minusHours(1));

        bomregistreringsKlient.sendInnPassering(bompassering1);
        bomregistreringsKlient.sendInnPassering(bompassering2);

        var krav = innsynKlient.hentAlleKravPåPerson(testperson.fnr());
        assertThat(krav).hasSize(2);
        assertThat(krav.get(0).beregningsgrunnlag().bompasseringer()).hasSize(1);
        assertThat(krav.get(1).beregningsgrunnlag().bompasseringer()).hasSize(1);
    }
}
