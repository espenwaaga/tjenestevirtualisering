package no.soprasteria.autotest.oppgaver.del2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.autotest.generator.ForbipasseringGenerator;
import no.soprasteria.autotest.klienter.vtp.VtpKlient;
import no.soprasteria.autotest.klienter.bomsystemet.BomregistreringsKlient;
import no.soprasteria.autotest.klienter.bomsystemet.InnsynKlient;

class Oppgave4 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);

    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final InnsynKlient kravKlient = new InnsynKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();

    @Test
    void sendInnForbipssdasseringerTilBomsystem() {
        var testperson = vtpKlient.opprettTestperson();
        var forbipassering = ForbipasseringGenerator.lagForbipassering(testperson.kjøretøy().registreringsnummer());
        var forbipasseringRegistrert = bomregistreringsKlient.sendInnPassering(forbipassering);
        assertThat(forbipasseringRegistrert).isTrue();

        var krav = kravKlient.hentAlleKravPåPerson(testperson.fnr());
        assertThat(krav).hasSize(1);
        assertThat(krav.get(0).beregningsgrunnlag().forbipasseringer()).hasSize(1);
    }
}
