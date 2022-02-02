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
import no.soprasteria.bomsystemet.util.database.Bompasseringsregister;
import no.soprasteria.bomsystemet.util.database.Kravregister;
import no.soprasteria.bomsystemet.innsyn.InnsynKontroller;
import no.soprasteria.bomsystemet.kravbehandling.Kravbehandler;
import no.soprasteria.bomsystemet.oppslag.KjøretøyOppslagKlient;
import no.soprasteria.bomsystemet.oppslag.PersonOppslagKlient;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Bompassering;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Bompasseringsinformasjon;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Sone;
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
    private Bompasseringsregister bompasseringsregister;
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
        beregning = new Beregning(bompasseringsregister, kjøretøyOppslag);
        kravbehandler = new Kravbehandler(kjøretøyOppslag, personOppslag, beregning, kravregister);
        innsynKontroller = new InnsynKontroller(kravregister, null);
    }


    @Test
    void verifiserKorrektAntallKravOpprettesForForenkletMetode() {
        var bompassering1 = new Bompassering(new Registreringsnummer("SV123123"),
                new Bompasseringsinformasjon(LocalDateTime.now().minusHours(4), Sone.SONE1));
        var bompassering2 = new Bompassering(new Registreringsnummer("SV123123"),
                new Bompasseringsinformasjon(LocalDateTime.now().minusHours(3).minusMinutes(30), Sone.SONE1));
        var bompassering3 = new Bompassering(new Registreringsnummer("SV123123"),
                new Bompasseringsinformasjon(LocalDateTime.now().minusHours(2).minusMinutes(45), Sone.SONE1));
        // Kronologisk
        kravbehandler.opprettKravPåPassering(bompassering1);
        kravbehandler.opprettKravPåPassering(bompassering2);
        kravbehandler.opprettKravPåPassering(bompassering3);
        assertThat(innsynKontroller.hentAlleKravPåPerson(fnr).size()).isEqualTo(2);
    }


    @Test
    void verifiserKorrektAntallKravOpprettesForopprettKravPåPasseringMedSafeGuardForForsinketRegistreringKronologisk() {
        var bompassering1 = new Bompassering(new Registreringsnummer("SV123123"),
                new Bompasseringsinformasjon(LocalDateTime.now().minusHours(4), Sone.SONE1));
        var bompassering2 = new Bompassering(new Registreringsnummer("SV123123"),
                new Bompasseringsinformasjon(LocalDateTime.now().minusHours(3).minusMinutes(30), Sone.SONE1));
        var bompassering3 = new Bompassering(new Registreringsnummer("SV123123"),
                new Bompasseringsinformasjon(LocalDateTime.now().minusHours(2).minusMinutes(45), Sone.SONE1));
        // Kronologisk
        kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(bompassering1);
        kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(bompassering2);
        kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(bompassering3);
        assertThat(innsynKontroller.hentAlleKravPåPerson(fnr).size()).isEqualTo(2);
    }



    @Test
    void verifiserKorrektAntallKravOpprettesForopprettKravPåPasseringMedSafeGuardForForsinketRegistreringIKKEKronologisk() {
        var bompassering1 = new Bompassering(new Registreringsnummer("SV123123"),
                new Bompasseringsinformasjon(LocalDateTime.now().minusHours(4), Sone.SONE1));
        var bompassering2 = new Bompassering(new Registreringsnummer("SV123123"),
                new Bompasseringsinformasjon(LocalDateTime.now().minusHours(3).minusMinutes(30), Sone.SONE1));
        var bompassering3 = new Bompassering(new Registreringsnummer("SV123123"),
                new Bompasseringsinformasjon(LocalDateTime.now().minusHours(2).minusMinutes(45), Sone.SONE1));
        // IKKE kronologisk!
        kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(bompassering3);
        kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(bompassering2);
        kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(bompassering1);
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
