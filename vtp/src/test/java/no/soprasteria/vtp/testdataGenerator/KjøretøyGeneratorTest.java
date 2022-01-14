package no.soprasteria.vtp.testdataGenerator;

import no.soprasteria.vtp.testdataGenerator.identer.FnrGenerator;
import org.junit.jupiter.api.Test;

class KjøretøyGeneratorTest {

    @Test
    public void testKjøretøyGenerator() {
        var kjøretøy = KjøretøyGenerator.lagFiktivtKjøretøy(FnrGenerator.tilfeldigMannFnr());
    }

}