package no.soprasteria.autotest.oppgaver.del2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.autotest.generator.ForbipasseringGenerator;
import no.soprasteria.autotest.klienter.vtp.VtpKlient;
import no.soprasteria.autotest.klienter.bomsystemet.BomregistreringsKlient;
import no.soprasteria.autotest.klienter.bomsystemet.KravKlient;

class Oppgave4 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);

    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final KravKlient kravKlient = new KravKlient();
    private static final VtpKlient vtpKlient = new VtpKlient();

    @Test
    void sendInnForbipssdasseringerTilBomsystem() {
        var testdata = vtpKlient.lagTestdata(1);
        var kjøretøy = testdata.kjøretøy().get(0);
        var fødselsnummer = kjøretøy.kjøretøyInfo().eier().fnr();
        var registreringsnummer = kjøretøy.registreringsnummer();
        var forbipassering = ForbipasseringGenerator.lagForbipassering(registreringsnummer);
        var forbipasseringRegistrert = bomregistreringsKlient.registererKjøretøy(forbipassering);
        assertThat(forbipasseringRegistrert).isTrue();

        var krav = kravKlient.hentAlleKravPåPerson(fødselsnummer);
        assertThat(krav).hasSize(1);
        assertThat(krav.get(0).beregningsgrunnlag().forbipasseringer()).hasSize(1);
    }
}
