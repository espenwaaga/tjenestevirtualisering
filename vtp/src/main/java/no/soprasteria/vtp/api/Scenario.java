package no.soprasteria.vtp.api;

import no.soprasteria.felles.kontrakter.vtp.TestscenarioData;
import no.soprasteria.vtp.register.Kjøretøyregister;
import no.soprasteria.vtp.register.Personregister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(no.soprasteria.vtp.mocks.PersonKontrollerMock.PERSON_PATH)
public class Scenario {
    private static final Logger LOG = LoggerFactory.getLogger(Scenario.class);
    private final Personregister personregister;
    private final Kjøretøyregister kjøretøyregister;

    @Autowired
    public Scenario(Personregister personregister, Kjøretøyregister kjøretøyregister) {
        this.personregister = personregister;
        this.kjøretøyregister = kjøretøyregister;
    }

    @PostMapping
    public void instansierTestscenario(@RequestBody TestscenarioData testscenarioData) {
        fyllRegisterMedScenarioInformasjon(testscenarioData);
    }


    @PostMapping
    public void instansierTestscenarioer(@RequestBody List<TestscenarioData> testscenarioData) {
        for (var data : testscenarioData) {
            fyllRegisterMedScenarioInformasjon(data);
        }
    }

    private void fyllRegisterMedScenarioInformasjon(TestscenarioData testscenarioData) {
        LOG.info("Fyller registrene med opplysninger for bruker {}", testscenarioData.person().fnr());
        var fnr = testscenarioData.person().fnr();
        var kjøretøy = testscenarioData.kjøretøy();

        personregister.add(fnr, testscenarioData.person());
//        kjøretøyregister.add(fnr, testscenarioData.personInformasjon());
    }
}
