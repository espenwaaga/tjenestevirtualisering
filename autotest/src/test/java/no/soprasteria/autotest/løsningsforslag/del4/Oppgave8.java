package no.soprasteria.autotest.løsningsforslag.del4;

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
class Oppgave8 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);
    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final KravKlient kravKlient = new KravKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();


    /**
     * Oppgave 8: Vi skal nå teste at bilisten blir bare belastet en gang hvis begge passeringene er innenfor en time fra hverandre.
     * I motsetning til oppgave 6 så skal vi nå unngå å sende disse inn kronologisk! Dette skal simulere store forsinkelser i en av bommene.
     *  1) Generer to forbipasseringer for gitt kjøretøy (maksimalt en times forskjell)
     *  2) Send inn disse to forbipasseringene i ikke-kronologisk rekkefølge (den siste passeringen sendes inn først)
     *  3) Hvor mange krav ville vi forvente nå? 1 eller 2?
     */
    @Test
    void hvordanHåndtereSystemetForsinkelserIInnsendingAvForbipasseringer() {
        var testperson = vtpKlient.opprettTestperson();
        var registreringsnummer = testperson.kjøretøy().registreringsnummer();

        var forbipassering1 = ForbipasseringGenerator.lagForbipassering(registreringsnummer, LocalDateTime.now().minusHours(1).minusMinutes(20));
        var forbipassering2 = ForbipasseringGenerator.lagForbipassering(registreringsnummer, LocalDateTime.now().minusHours(1));

        bomregistreringsKlient.sendInnPassering(forbipassering2);
        bomregistreringsKlient.sendInnPassering(forbipassering1);

        var krav = kravKlient.hentAlleKravPåPerson(testperson.fnr());
        assertThat(krav).hasSize(1);

        /**
         * Husk å endre variabelen 'forbipasseringer-ikke-kronologisk' som er definert i application-del2-4.yml under bomsystemet.
         * Sett denne verdien til 'true'. Etter endringen må du også huske å kjøre ned og opp bomsystemet igjen.
         */

    }
}
