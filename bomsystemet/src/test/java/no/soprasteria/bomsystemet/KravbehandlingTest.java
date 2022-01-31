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
import no.soprasteria.bomsystemet.util.config.JacksonConfiguration;
import no.soprasteria.bomsystemet.util.config.RegisterConfiguration;
import no.soprasteria.bomsystemet.util.database.Forbipasseringsregister;
import no.soprasteria.bomsystemet.util.database.Kravregister;
import no.soprasteria.bomsystemet.innsyn.InnsynKontroller;
import no.soprasteria.bomsystemet.kravbehandling.Kravbehandler;
import no.soprasteria.bomsystemet.oppslag.kjøretøy.KjøretøyOppslagKlient;
import no.soprasteria.bomsystemet.oppslag.person.PersonOppslagKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipassering;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipasseringsinformasjon;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Sone;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Eier;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;
import no.soprasteria.felles.kontrakter.bomsystem.person.Adresse;
import no.soprasteria.felles.kontrakter.bomsystem.person.Navn;
import no.soprasteria.felles.kontrakter.bomsystem.person.Person;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        JacksonConfiguration.class,
        RegisterConfiguration.class
})
class KravbehandlingTest {

    private static final Fødselsnummer fnr = new Fødselsnummer("33333344444");

    @Autowired
    private Forbipasseringsregister forbipasseringsregister;
    private Kravregister kravregister = new Kravregister();
    private Beregning beregning;
    private Kravbehandler kravbehandler;
    private InnsynKontroller innsynKontroller;


    @Mock
    private KjøretøyOppslagKlient kjøretøyOppslag;
    @Mock
    private PersonOppslagKlient personOppslag;


    private static final KjøretøyInfo kjøretøyInfo = kjøretøyInfo();
    private static final Person personInfo = personInfo();

    @BeforeEach
    public void førAlleTester() {
        when(kjøretøyOppslag.hentInformasjonOmKjøretøy(any())).thenReturn(kjøretøyInfo);
        when(personOppslag.hentOpplysninger(any())).thenReturn(personInfo);
        beregning = new Beregning(forbipasseringsregister, kjøretøyOppslag);
        kravbehandler = new Kravbehandler(kjøretøyOppslag, personOppslag, beregning, kravregister);
        innsynKontroller = new InnsynKontroller(kravregister, null);
    }


    @Test
    void verifiserKorrektAntallKravOpprettesForForenkletMetode() {
        var forbipassering1 = new Forbipassering(new Registreringsnummer("SV123123"),
                new Forbipasseringsinformasjon(LocalDateTime.now().minusHours(4), Sone.SONE1));
        var forbipassering2 = new Forbipassering(new Registreringsnummer("SV123123"),
                new Forbipasseringsinformasjon(LocalDateTime.now().minusHours(3).minusMinutes(30), Sone.SONE1));
        var forbipassering3 = new Forbipassering(new Registreringsnummer("SV123123"),
                new Forbipasseringsinformasjon(LocalDateTime.now().minusHours(2).minusMinutes(45), Sone.SONE1));
        // Kronologisk
        kravbehandler.opprettKravPåPassering(forbipassering1);
        kravbehandler.opprettKravPåPassering(forbipassering2);
        kravbehandler.opprettKravPåPassering(forbipassering3);
        assertThat(innsynKontroller.hentAlleKravPåPerson(fnr).size()).isEqualTo(2);
    }


    @Test
    void verifiserKorrektAntallKravOpprettesForopprettKravPåPasseringMedSafeGuardForForsinketRegistreringKronologisk() {
        var forbipassering1 = new Forbipassering(new Registreringsnummer("SV123123"),
                new Forbipasseringsinformasjon(LocalDateTime.now().minusHours(4), Sone.SONE1));
        var forbipassering2 = new Forbipassering(new Registreringsnummer("SV123123"),
                new Forbipasseringsinformasjon(LocalDateTime.now().minusHours(3).minusMinutes(30), Sone.SONE1));
        var forbipassering3 = new Forbipassering(new Registreringsnummer("SV123123"),
                new Forbipasseringsinformasjon(LocalDateTime.now().minusHours(2).minusMinutes(45), Sone.SONE1));
        // Kronologisk
        kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(forbipassering1);
        kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(forbipassering2);
        kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(forbipassering3);
        assertThat(innsynKontroller.hentAlleKravPåPerson(fnr).size()).isEqualTo(2);
    }



    @Test
    void verifiserKorrektAntallKravOpprettesForopprettKravPåPasseringMedSafeGuardForForsinketRegistreringIKKEKronologisk() {
        var forbipassering1 = new Forbipassering(new Registreringsnummer("SV123123"),
                new Forbipasseringsinformasjon(LocalDateTime.now().minusHours(4), Sone.SONE1));
        var forbipassering2 = new Forbipassering(new Registreringsnummer("SV123123"),
                new Forbipasseringsinformasjon(LocalDateTime.now().minusHours(3).minusMinutes(30), Sone.SONE1));
        var forbipassering3 = new Forbipassering(new Registreringsnummer("SV123123"),
                new Forbipasseringsinformasjon(LocalDateTime.now().minusHours(2).minusMinutes(45), Sone.SONE1));
        // IKKE kronologisk!
        kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(forbipassering3);
        kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(forbipassering2);
        kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(forbipassering1);
        assertThat(innsynKontroller.hentAlleKravPåPerson(fnr).size()).isEqualTo(2);
    }

    private static Person personInfo() {
        return new Person(
                new Navn("Ola", "Nordmann"),
                fnr,
                new Adresse("navn", 12, "0812", "Oslo"),
                "test@hotmail.no");
    }

    private static KjøretøyInfo kjøretøyInfo() {
        return new KjøretøyInfo(new Eier(fnr), KjøretøyKlasse.KLASSE2);
    }
}
