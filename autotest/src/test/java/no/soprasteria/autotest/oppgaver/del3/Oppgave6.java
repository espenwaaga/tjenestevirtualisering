package no.soprasteria.autotest.oppgaver.del3;

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
class Oppgave6 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);

    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final KravKlient kravKlient = new KravKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();

    /**
     * Oppgave 6: Vi skal nå teste at bilisten blir bare belastet en gang hvis begge passeringene er innenfor en time fra hverandre.
     *  1) Generer to forbipasseringer for gitt kjøretøy (maksimalt en times forskjell)
     *  2) Send inn disse to forbipasseringene i kronologisk rekkefølge.
     *  3) Verifiser at det blir bare opprettet ett krav.
     */
    @Test
    void toEllerFlereForbipasseringerForInneværendeTimeSkalBareBelastesEnGang() {
        // Skriv test her

    }
}
