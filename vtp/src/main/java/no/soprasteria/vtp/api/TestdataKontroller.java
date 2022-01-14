package no.soprasteria.vtp.api;

import no.soprasteria.felles.kontrakter.vtp.Testdata;
import no.soprasteria.vtp.register.Kjøretøyregister;
import no.soprasteria.vtp.register.Personregister;
import no.soprasteria.vtp.testdataGenerator.TestdataGenerator;
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

@RestController()
@RequestMapping(TestdataKontroller.TESTDATA_PATH)
public class TestdataKontroller {

    public static final String TESTDATA_PATH = "/testdata";

    private static final Logger LOG = LoggerFactory.getLogger(TestdataKontroller.class);
    private final Personregister personregister;
    private final Kjøretøyregister kjøretøyregister;

    @Autowired
    public TestdataKontroller(Personregister personregister, Kjøretøyregister kjøretøyregister) {
        this.personregister = personregister;
        this.kjøretøyregister = kjøretøyregister;
    }

    @GetMapping(value = "/{antallKjøretøy}")
    public Testdata genererTestdata(@PathVariable("antallKjøretøy") int antallKjøretøy) {
        var testdata = TestdataGenerator.lagTestdata(antallKjøretøy);
        if (CollectionUtils.isEmpty(testdata.person()) ||CollectionUtils.isEmpty(testdata.kjøretøy())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Feil i generering av testdata!");
        }
        fyllRegisterMedScenarioInformasjon(testdata);
        return testdata;
    }

    private void fyllRegisterMedScenarioInformasjon(Testdata testdata) {
        LOG.info("Fyller registrene med antall kjøretøy {}", testdata.kjøretøy().size());

        personregister.add(testdata.person());
        kjøretøyregister.add(testdata.kjøretøy());

        LOG.info("Fyllt register med testdata");

    }

}
