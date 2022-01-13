package no.soprasteria.vtp.api;

import no.soprasteria.felles.kontrakter.vtp.TestdataBestilling;
import no.soprasteria.vtp.register.Kjøretøyregister;
import no.soprasteria.vtp.register.Personregister;
import no.soprasteria.vtp.testdataGenerator.KjøretøyGenerator;
import no.soprasteria.vtp.testdataGenerator.PersonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(Scenario.SCENARIO_PATH)
public class Scenario {

    public static final String SCENARIO_PATH= "/scenario";
    private static final Logger LOG = LoggerFactory.getLogger(Scenario.class);
    private final Personregister personregister;
    private final Kjøretøyregister kjøretøyregister;

    public final PersonGenerator personGenerator = new PersonGenerator();
    public final KjøretøyGenerator kjøretøyGenerator = new KjøretøyGenerator();

    @Autowired
    public Scenario(Personregister personregister, Kjøretøyregister kjøretøyregister) {
        this.personregister = personregister;
        this.kjøretøyregister = kjøretøyregister;
    }

    @PostMapping
    public void instansierTestscenario(@RequestBody TestdataBestilling testdataBestilling) {
        fyllRegisterMedScenarioInformasjon(testdataBestilling);
    }

    private void fyllRegisterMedScenarioInformasjon(TestdataBestilling testdataBestilling) {
        LOG.info("Fyller registrene med bruker {}", testdataBestilling.getAntallPersoner());

        for (int i = 0 ; i < testdataBestilling.getAntallPersoner() ; i++) {
            var person = personGenerator.lagFiktivPerson();
            personregister.add(person);
            kjøretøyregister.add(kjøretøyGenerator.lagFiktivtKjøretøy(person.fnr()));
        }

    }

}
