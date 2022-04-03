package no.soprasteria.autotest.oppgaver.del2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.soprasteria.autotest.generator.BompasseringGenerator;
import no.soprasteria.autotest.klienter.vtp.VtpTestdataKlient;
import no.soprasteria.autotest.klienter.bomsystemet.BomregistreringsKlient;
import no.soprasteria.autotest.klienter.bomsystemet.InnsynKlient;

class Oppgave4 {
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringsKlient.class);
    private static final BomregistreringsKlient bomregistreringsKlient = new BomregistreringsKlient();
    private static final InnsynKlient innsynKlient = new InnsynKlient();
    private static final VtpTestdataKlient vtpTestdataKlient = new VtpTestdataKlient();

    /**
     * Her skal vi nå gjøre det vi gjorde i del 1 i ett jafs:
     * Vi skal lage en mock av personregisteret hos Skatteetaten.
     * Vi skal redirecte traffikk fra Skatt mot Mocken vår i VTP.
     */
    @Test
    void sendInnForbipssdasseringerTilBomsystem() {
        var testperson = vtpTestdataKlient.opprettTestperson();
        var bompassering = BompasseringGenerator.lagBompassering(testperson.kjøretøy().registreringsnummer());
        var bompasseringRegistrert = bomregistreringsKlient.sendInnPassering(bompassering);
        assertThat(bompasseringRegistrert).isTrue();

        var krav = innsynKlient.hentAlleKravPåPerson(testperson.fnr());
        assertThat(krav).hasSize(1);
        assertThat(krav.get(0).beregningsgrunnlag().bompasseringer()).hasSize(1);
    }
}
