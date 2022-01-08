package no.soprasteria.bomsystemet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import no.soprasteria.bomsystemet.beregning.Beregning;
import no.soprasteria.bomsystemet.config.JacksonConfiguration;
import no.soprasteria.bomsystemet.config.RegisterConfiguration;
import no.soprasteria.bomsystemet.database.Forbipasseringsregister;
import no.soprasteria.bomsystemet.oppslag.kjøretøy.KjøretøyOppslagKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipasseringsinformasjon;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Sone;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Eier;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        JacksonConfiguration.class,
        RegisterConfiguration.class
})
class BeregningTest {

    @Autowired
    private Forbipasseringsregister forbipasseringsregister;
    @Mock
    private KjøretøyOppslagKlient oppslag;
    private Beregning beregning;


    private static final KjøretøyInfo kjøretøyInfo = kjøretøyInfo();

    @BeforeEach
    public void førAlleTester() {
        when(oppslag.hentInformasjonOmKjøretøy(any())).thenReturn(kjøretøyInfo);
        beregning = new Beregning(forbipasseringsregister, oppslag);
    }

    @Test
    void forventerAtAvgiftenBlirNullVedIngenPasseringer() {
        var avgift = beregning.beregnVeiavgift(new Registreringsnummer("SV123456"));
        assertThat(avgift).isZero();
    }

    @Test
    void forventerAvgiftVedEnPassering() {
        var registerringsnummer = new Registreringsnummer("SV242526");
        forbipasseringsregister.add(registerringsnummer, new Forbipasseringsinformasjon(LocalDate.now(), Sone.SONE1));
        var avgift = beregning.beregnVeiavgift(registerringsnummer);
        assertThat(avgift).isEqualTo(24);

        forbipasseringsregister.remove(registerringsnummer);
        assertThat(forbipasseringsregister.get(registerringsnummer).size()).isZero();
    }



    @Test
    void forventerAvgiftForBeggePasseringene() {
        var registerringsnummer = new Registreringsnummer("SV242526");
        forbipasseringsregister.add(registerringsnummer, new Forbipasseringsinformasjon(LocalDate.now(), Sone.SONE1));
        forbipasseringsregister.add(registerringsnummer, new Forbipasseringsinformasjon(LocalDate.now().minusDays(1), Sone.SONE1));
        var avgift = beregning.beregnVeiavgift(registerringsnummer);
        assertThat(avgift).isEqualTo(48);
        forbipasseringsregister.remove(registerringsnummer);
        assertThat(forbipasseringsregister.get(registerringsnummer).size()).isZero();
    }

    private static KjøretøyInfo kjøretøyInfo() {
        return new KjøretøyInfo(new Eier(new Fødselsnummer("33333344444")), KjøretøyKlasse.KLASSE2);
    }
}
