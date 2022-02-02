package no.soprasteria.bomsystemet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import no.soprasteria.bomsystemet.beregning.Beregning;
import no.soprasteria.bomsystemet.util.config.RegisterConfiguration;
import no.soprasteria.bomsystemet.util.database.Bompasseringsregister;
import no.soprasteria.bomsystemet.oppslag.KjøretøyOppslagKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Bompasseringsinformasjon;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Sone;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Eier;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RegisterConfiguration.class)
class BeregningTest {

    @Autowired
    private Bompasseringsregister bompasseringsregister;
    @Mock
    private KjøretøyOppslagKlient oppslag;
    private Beregning beregning;


    private static final KjøretøyInfo kjøretøyInfo = kjøretøyInfo();

    @BeforeEach
    public void førAlleTester() {
        when(oppslag.hentInformasjonOmKjøretøy(any())).thenReturn(kjøretøyInfo);
        beregning = new Beregning(bompasseringsregister, oppslag);
    }

    @Test
    void forventerAtAvgiftenBlirNullVedIngenPasseringer() {
        var avgift = beregning.beregnVeiavgift(new Registreringsnummer("SV123456"));
        assertThat(avgift).isZero();
    }

    @Test
    void forventerAvgiftVedEnPassering() {
        var registerringsnummer = new Registreringsnummer("SV242526");
        bompasseringsregister.add(registerringsnummer, new Bompasseringsinformasjon(LocalDateTime.now(), Sone.SONE1));
        var avgift = beregning.beregnVeiavgift(registerringsnummer);
        assertThat(avgift).isEqualTo(24);

        bompasseringsregister.remove(registerringsnummer);
        assertThat(bompasseringsregister.get(registerringsnummer).size()).isZero();
    }



    @Test
    void forventerAvgiftForBeggePasseringene() {
        var registerringsnummer = new Registreringsnummer("SV242526");
        bompasseringsregister.add(registerringsnummer, new Bompasseringsinformasjon(LocalDateTime.now(), Sone.SONE1));
        bompasseringsregister.add(registerringsnummer, new Bompasseringsinformasjon(LocalDateTime.now().minusDays(1), Sone.SONE1));
        var avgift = beregning.beregnVeiavgift(registerringsnummer);
        assertThat(avgift).isEqualTo(48);
        bompasseringsregister.remove(registerringsnummer);
        assertThat(bompasseringsregister.get(registerringsnummer).size()).isZero();
    }

    private static KjøretøyInfo kjøretøyInfo() {
        return new KjøretøyInfo(new Eier(new Fødselsnummer("33333344444")), KjøretøyKlasse.KLASSE2);
    }
}
