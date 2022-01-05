package no.soprasteria.autotest.registrering;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import no.soprasteria.autotest.klienter.bomsystemet.InnsendingKlient;
import no.soprasteria.felles.bomsystem.Forbipassering;
import no.soprasteria.felles.bomsystem.Forbipasseringsinformasjon;
import no.soprasteria.felles.bomsystem.Registreringsnummer;
import no.soprasteria.felles.bomsystem.Sone;

class KjøretøyregistreringTest {

    private InnsendingKlient innsendingKlient = new InnsendingKlient();

    @Test
    public void testKjøretøyregisterring() {
        var forbipassering1 = new Forbipassering(new Registreringsnummer("SV245321"),
                new Forbipasseringsinformasjon(LocalDate.now(), Sone.SONE1));

        innsendingKlient.registererKjøretøy(forbipassering1);

    }
}
