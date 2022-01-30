package no.soprasteria.vtp.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.soprasteria.felles.kontrakter.vtp.Testpersoner;
import no.soprasteria.felles.kontrakter.vtp.Testperson;
import no.soprasteria.vtp.register.Kjøretøyregister;
import no.soprasteria.vtp.register.Personregister;
import no.soprasteria.vtp.testdataGenerator.TestdataGenerator;

@RestController()
@RequestMapping(TestdataKontroller.TESTDATA_PATH)
public class TestdataKontroller {

    public static final String TESTDATA_PATH = "/testdata";

    private static final Logger LOG = LoggerFactory.getLogger(TestdataKontroller.class);
    private final Kjøretøyregister kjøretøyregister;
    private final Personregister personregister;

    @Autowired
    public TestdataKontroller(Kjøretøyregister kjøretøyregister, Personregister personregister) {
        this.kjøretøyregister = kjøretøyregister;
        this.personregister = personregister;
    }

    @PostMapping(path = "/person")
    public Testperson opprettTestperson(@RequestBody int antallKjøretøy) {
        var testperson = TestdataGenerator.lagTestperson(antallKjøretøy);
        fyllRegisterMedScenarioInformasjon(testperson);
        return testperson;
    }

    @PostMapping(path = "/personer")
    public Testpersoner opprettTestpersoner(@RequestBody int antallPersoner) {
        List<Testperson> testpersoner = new ArrayList<>();
        for (var i=1; i <= antallPersoner; i++) {
            testpersoner.add(opprettTestperson(1));
        }
        return new Testpersoner(testpersoner);
    }

    private void fyllRegisterMedScenarioInformasjon(Testperson testperson) {
        LOG.info("Legger til person med fnr {} i personregisteret og registrert følgende kjøretøy på personen {}", testperson.fnr(), testperson.registreringnummereForAlleRegistreteKjøretøy());
        personregister.add(testperson.person());
        kjøretøyregister.add(testperson.kjøretøy());
    }
}
