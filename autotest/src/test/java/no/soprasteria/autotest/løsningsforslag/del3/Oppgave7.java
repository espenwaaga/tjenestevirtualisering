package no.soprasteria.autotest.løsningsforslag.del3;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.autotest.generator.ForbipasseringGenerator;
import no.soprasteria.autotest.klienter.bomsystemet.BomregistreringsKlient;
import no.soprasteria.autotest.klienter.bomsystemet.InnsynKlient;
import no.soprasteria.autotest.klienter.vtp.VtpKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;


/**
 * Legg merke til at ForbipasseringGenerator også har en metode for å spesifisere tidspunkt
 * {@link ForbipasseringGenerator#lagForbipassering(Registreringsnummer, LocalDateTime)}
 * LocalDateTime kan du enkelt spesifiser absolutt tidspunkt slik -> LocalDateTime.of(2022, 02, 14, 2, 2, 2),
 * eller relativt tidspunkt -> LocalDateTime.now().minusHours(1)
 */
class Oppgave7 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);

    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final InnsynKlient kravKlient = new InnsynKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();

    /**
     * Oppgave 7: Nå skal vi teste at en person ikke blir belastet for andres forbipasseringer.
     *  1) Send inn bestemt antall forbipasseringer for person #1.
     *  2) Deretter opprett en eller flere nye testpersoner og send inn forbipassering for disse også.
     *  3) Verifiser at person #1 bare blir belastet for sine forbipasseringer, og ikke andre sine.
     */
    @Test
    void personeSkalBareBliBelastesForPasseringerForKjøretøySomErRegistrertPåSegSelv() {
        var testperson1 = vtpKlient.opprettTestperson();
        var forbipassering1_1 = ForbipasseringGenerator.lagForbipassering(testperson1.kjøretøy().registreringsnummer(), LocalDateTime.now().minusHours(2));
        bomregistreringsKlient.sendInnPassering(forbipassering1_1);
        var opprinneligKrav = kravKlient.hentAlleKravPåPerson(testperson1.fnr());
        assertThat(opprinneligKrav).hasSize(1);


        // Person2
        var testperson2 = vtpKlient.opprettTestperson();
        var forbipasseringPerson2_1 = ForbipasseringGenerator.lagForbipassering(testperson2.kjøretøy().registreringsnummer(), LocalDateTime.now().minusHours(3));
        var forbipasseringPerson2_2 = ForbipasseringGenerator.lagForbipassering(testperson2.kjøretøy().registreringsnummer(), LocalDateTime.now().minusHours(2).minusMinutes(4));
        bomregistreringsKlient.sendInnPassering(forbipasseringPerson2_1);
        bomregistreringsKlient.sendInnPassering(forbipasseringPerson2_2);

        // Person 3
        var testperson3 = vtpKlient.opprettTestperson();
        var forbipasseringPerson3_1 = ForbipasseringGenerator.lagForbipassering(testperson3.kjøretøy().registreringsnummer(), LocalDateTime.now().minusHours(2).minusSeconds(5));
        bomregistreringsKlient.sendInnPassering(forbipasseringPerson3_1);

        var kravEtterPerson2Og3ErRegistrert = kravKlient.hentAlleKravPåPerson(testperson1.fnr());
        assertThat(kravEtterPerson2Og3ErRegistrert)
                .hasSize(1)
                .isEqualTo(opprinneligKrav);
    }
}
