package no.soprasteria.autotest.registrering;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import no.soprasteria.autotest.klienter.bomsystemet.InnsendingKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipassering;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipasseringsinformasjon;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Sone;

class KjøretøyregistreringTest {

    private final InnsendingKlient innsendingKlient = new InnsendingKlient();

    @Test
    void testKjøretøyregisterring() {
        var forbipassering1 = new Forbipassering(new Registreringsnummer("SV245321"),
                new Forbipasseringsinformasjon(LocalDate.now(), Sone.SONE1));

        innsendingKlient.registererKjøretøy(forbipassering1);

    }
}
