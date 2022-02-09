package no.soprasteria.autotest.oppgaver.del3;

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
class Oppgave7 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);
    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final InnsynKlient kravKlient = new InnsynKlient();
    private static final VtpTestdataKlient vtpTestdataKlient = new VtpTestdataKlient();

    /**
     * Oppgave 7: Nå skal vi teste at en person ikke blir belastet for andres bompasseringer.
     *  1) Send inn bestemt antall bompasseringer for person #1.
     *  2) Deretter opprett en eller flere nye testpersoner og send inn bompassering for disse også.
     *  3) Verifiser at person #1 bare blir belastet for sine bompasseringer, og ikke andre sine.
     */
    @Test
    void personeSkalBareBliBelastesForPasseringerForKjøretøySomErRegistrertPåSegSelv() {
        // Skriv test her

    }
}
