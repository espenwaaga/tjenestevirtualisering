package no.soprasteria.vtp.testdata;

import no.soprasteria.vtp.util.testdata.KjøretøyGenerator;
import no.soprasteria.vtp.util.testdata.person.FnrGenerator;
import org.junit.jupiter.api.Test;

class KjøretøyGeneratorTest {

    @Test
    public void testKjøretøyGenerator() {
        var kjøretøy = KjøretøyGenerator.lagFiktivtKjøretøy(FnrGenerator.tilfeldigMannFnr());
    }

}
