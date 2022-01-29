package no.soprasteria.vtp.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyKlasse;
import no.soprasteria.felles.kontrakter.vtp.Testdata;
import no.soprasteria.vtp.register.Kjøretøyregister;
import no.soprasteria.vtp.register.Personregister;
import no.soprasteria.vtp.testdataGenerator.KjøretøyGenerator;
import no.soprasteria.vtp.testdataGenerator.PersonGenerator;
import no.soprasteria.vtp.testdataGenerator.TestdataGenerator;

@RestController()
@RequestMapping(TestdataKontroller.TESTDATA_PATH)
public class TestdataKontroller {

    public static final String TESTDATA_PATH = "/testdata";
    private static final String ANTALL_KJØRETØY_PATH_PARAM = "antallKjøretøy";
    private static final String KJØRETØY_KLASSE_PATH_PARAM = "kjøretøyKlasse";

    private static final Logger LOG = LoggerFactory.getLogger(TestdataKontroller.class);
    private final Personregister personregister;
    private final Kjøretøyregister kjøretøyregister;

    @Autowired
    public TestdataKontroller(Personregister personregister, Kjøretøyregister kjøretøyregister) {
        this.personregister = personregister;
        this.kjøretøyregister = kjøretøyregister;
    }


    // TODO: Er ikke en GET. Her har vi en POST.
    @GetMapping(value = "/{" + ANTALL_KJØRETØY_PATH_PARAM + "}")
    public Testdata genererTestdata(@PathVariable(ANTALL_KJØRETØY_PATH_PARAM) int antallKjøretøy) {
        var testdata = TestdataGenerator.lagTestdata(antallKjøretøy);
        if (CollectionUtils.isEmpty(testdata.person()) ||CollectionUtils.isEmpty(testdata.kjøretøy())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Feil i generering av testdata!");
        }
        fyllRegisterMedScenarioInformasjon(testdata);
        return testdata;
    }

    @GetMapping(value = "/kjøretøy/{" + KJØRETØY_KLASSE_PATH_PARAM + "}")
    public Testdata registrerKjøretøy(@PathVariable(KJØRETØY_KLASSE_PATH_PARAM) KjøretøyKlasse kjøretøyKlasse) {
        LOG.info("Lager bil av klasse: {}", kjøretøyKlasse);

        var person = PersonGenerator.lagFiktivPerson();
        var kjøretøy = KjøretøyGenerator.lagFiktivtKjøretøy(person.fnr());

        var testdata = new Testdata(List.of(person), List.of(kjøretøy));
        fyllRegisterMedScenarioInformasjon(testdata);
        return testdata;
    }

    private void fyllRegisterMedScenarioInformasjon(Testdata testdata) {
        LOG.info("START Fyller registrene med antall kjøretøy {}", testdata.kjøretøy().size());

        personregister.add(testdata.person());
        kjøretøyregister.add(testdata.kjøretøy());

        LOG.info("SLUTT Fyllt register med testdata");
        LOG.info("Antall enehter i personregister: {}", personregister.getSize());
        LOG.info("Antall enehter i kjøretøyregister: {}", kjøretøyregister.getSize());

    }

}
