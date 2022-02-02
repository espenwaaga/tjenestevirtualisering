package no.soprasteria.autotest.løsningsforslag.del2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.autotest.generator.BompasseringGenerator;
import no.soprasteria.autotest.klienter.bomsystemet.BomregistreringsKlient;
import no.soprasteria.autotest.klienter.bomsystemet.InnsynKlient;
import no.soprasteria.autotest.klienter.vtp.VtpKlient;

class Oppgave4 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);

    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final InnsynKlient innsynKlient = new InnsynKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();

    @Test
    void sendInnForbipssdasseringerTilBomsystem() {
        var testperson = vtpKlient.opprettTestperson();
        var bompassering = BompasseringGenerator.lagBompassering(testperson.kjøretøy().registreringsnummer());
        var bompasseringErRegistrert = bomregistreringsKlient.sendInnPassering(bompassering);
        assertThat(bompasseringErRegistrert).isTrue();

        var krav = innsynKlient.hentAlleKravPåPerson(testperson.fnr());
        assertThat(krav).hasSize(1);
        assertThat(krav.get(0).beregningsgrunnlag().bompasseringer()).hasSize(1);
    }
}
