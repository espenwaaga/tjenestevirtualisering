package no.soprasteria.autotest.løsningsforslag.del3;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.autotest.generator.BompasseringGenerator;
import no.soprasteria.autotest.klienter.bomsystemet.BomregistreringsKlient;
import no.soprasteria.autotest.klienter.bomsystemet.InnsynKlient;
import no.soprasteria.autotest.klienter.vtp.VtpKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;


/**
 * Legg merke til at BompasseringGenerator også har en metode for å spesifisere tidspunkt
 * {@link BompasseringGenerator#lagBompassering(Registreringsnummer, LocalDateTime)}
 * LocalDateTime kan du enkelt spesifiser absolutt tidspunkt slik -> LocalDateTime.of(2022, 02, 14, 2, 2, 2),
 * eller relativt tidspunkt -> LocalDateTime.now().minusHours(1)
 */
class Oppgave6 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);

    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final InnsynKlient kravKlient = new InnsynKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();

    /**
     * Oppgave 6: Vi skal nå teste at bilisten blir bare belastet en gang hvis begge passeringene er innenfor en time fra hverandre.
     *  1) Generer to bompasseringer for gitt kjøretøy (maksimalt en times forskjell)
     *  2) Send inn disse to bompasseringene i kronologisk rekkefølge.
     *  3) Verifiser at det blir bare opprettet ett krav.
     */
    @Test
    void toEllerFlereBompasseringerForInneværendeTimeSkalBareBelastesEnGang() {
        var testperson = vtpKlient.opprettTestperson();
        var registreringsnummer = testperson.kjøretøy().registreringsnummer();

        var bompassering1 = BompasseringGenerator.lagBompassering(registreringsnummer, LocalDateTime.now().minusHours(1).minusMinutes(30));
        var bompassering2 = BompasseringGenerator.lagBompassering(registreringsnummer, LocalDateTime.now().minusHours(1));

        bomregistreringsKlient.sendInnPassering(bompassering1);
        bomregistreringsKlient.sendInnPassering(bompassering2);

        var krav = kravKlient.hentAlleKravPåPerson(testperson.fnr());
        assertThat(krav).hasSize(1);
        assertThat(krav.get(0).beregningsgrunnlag().bompasseringer()).hasSize(2);
    }
}
