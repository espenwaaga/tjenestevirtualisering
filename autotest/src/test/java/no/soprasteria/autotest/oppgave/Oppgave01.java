package no.soprasteria.autotest.oppgave;

import no.soprasteria.autotest.generator.ForbipasseringGenerator;
import no.soprasteria.autotest.klienter.bomsystemet.BomsystemKlient;
import no.soprasteria.autotest.klienter.vtp.VtpKlient;
import org.junit.jupiter.api.Test;

public class Oppgave01 {

    private static final VtpKlient vtpKlient = new VtpKlient();
    private static final BomsystemKlient bomsystemKlient = new BomsystemKlient();

    @Test
    public void sendInnForbipasseringerTilBomsystem() {
        //Lag Testdata 20 biler

        var testdata = vtpKlient.lagTestdata(20);

        var forbibassering = ForbipasseringGenerator.lagForbipassering(testdata.kjøretøy());

        bomsystemKlient.registererKjøretøy(forbibassering);
    }
}
