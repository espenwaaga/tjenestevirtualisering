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
class Oppgave7 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);

    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final InnsynKlient innsynKlient = new InnsynKlient();
    private static final VtpTestdataKlient vtpTestdataKlient = new VtpTestdataKlient();

    /**
     * Oppgave 7: Nå skal vi teste at en person ikke blir belastet for andres bompasseringer.
     *  1) Send inn bestemt antall bompasseringer for person #1.
     *  2) Deretter opprett en eller flere nye testpersoner og send inn bompassering for disse også.
     *  3) Verifiser at person #1 bare blir belastet for sine bompasseringer, og ikke andre sine.
     */
    @Test
    void personeSkalBareBliBelastesForPasseringerForKjøretøySomErRegistrertPåSegSelv() {
        var testperson1 = vtpTestdataKlient.opprettTestperson();
        var bompassering1_1 = BompasseringGenerator.lagBompassering(testperson1.kjøretøy().registreringsnummer(), LocalDateTime.now().minusHours(2));
        bomregistreringsKlient.sendInnPassering(bompassering1_1);
        var opprinneligKrav = innsynKlient.hentAlleKravPåPerson(testperson1.fnr());
        assertThat(opprinneligKrav).hasSize(1);


        // Person2
        var testperson2 = vtpTestdataKlient.opprettTestperson();
        var bompasseringPerson2_1 = BompasseringGenerator.lagBompassering(testperson2.kjøretøy().registreringsnummer(), LocalDateTime.now().minusHours(3));
        var bompasseringPerson2_2 = BompasseringGenerator.lagBompassering(testperson2.kjøretøy().registreringsnummer(), LocalDateTime.now().minusHours(2).minusMinutes(4));
        bomregistreringsKlient.sendInnPassering(bompasseringPerson2_1);
        bomregistreringsKlient.sendInnPassering(bompasseringPerson2_2);

        // Person 3
        var testperson3 = vtpTestdataKlient.opprettTestperson();
        var bompasseringPerson3_1 = BompasseringGenerator.lagBompassering(testperson3.kjøretøy().registreringsnummer(), LocalDateTime.now().minusHours(2).minusSeconds(5));
        bomregistreringsKlient.sendInnPassering(bompasseringPerson3_1);

        var kravEtterPerson2Og3ErRegistrert = innsynKlient.hentAlleKravPåPerson(testperson1.fnr());
        assertThat(kravEtterPerson2Og3ErRegistrert)
                .hasSize(1)
                .isEqualTo(opprinneligKrav);
    }
}
