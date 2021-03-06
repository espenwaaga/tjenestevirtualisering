package no.soprasteria.autotest.oppgaver.del4;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.autotest.generator.BompasseringGenerator;
import no.soprasteria.autotest.klienter.bomsystemet.BomregistreringsKlient;
import no.soprasteria.autotest.klienter.bomsystemet.InnsynKlient;
import no.soprasteria.autotest.klienter.vtp.VtpSimulerIkkeFunskjonelleKravKlient;
import no.soprasteria.autotest.klienter.vtp.VtpTestdataKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;

/**
 * Legg merke til at BompasseringGenerator også har en metode for å spesifisere tidspunkt
 * {@link BompasseringGenerator#lagBompassering(Registreringsnummer, LocalDateTime)}
 * LocalDateTime kan du enkelt spesifiser absolutt tidspunkt slik -> LocalDateTime.of(2022, 02, 14, 2, 2, 2),
 * eller relativt tidspunkt -> LocalDateTime.now().minusHours(1)
 */
class Oppgave9 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);
    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final InnsynKlient innsynKlient = new InnsynKlient();
    private static final VtpTestdataKlient vtpTestdataKlient = new VtpTestdataKlient();
    private static final VtpSimulerIkkeFunskjonelleKravKlient vtpSimulerIkkeFunksjonlleKravKlient = new VtpSimulerIkkeFunskjonelleKravKlient();


    /**
     * Oppgave 9: Nå skal vi sjekke hvordan systemet håndtere forsinkelser hos skattetaten og vegvesenet. Vi skal nå
     * legge på forsinkelse/delay på alle kall som går mot skattetaten og vegvesenet. Dette kan gjøres med klienten
     * 'SimulerIkkeFunskjonelleKravKlient'.
     *
     *  1) Legg på delay på for eksempel 10 sekunder.
     *  2) Send inn bompasseringer og sjekk oppførselen til systemet
     */
    @Test
    void delayHosVegvesenetOgSkattetaten() {




    }


    /**
     * Denne @AfterEach metoden kjøres etter hver testkjøring i denne klassen.
     * Her tar vi å resetter kontrolleren tilbake til normal tilstand.
     */
    @AfterEach
    public void reset() {
        vtpSimulerIkkeFunksjonlleKravKlient.reset();
    }

}
