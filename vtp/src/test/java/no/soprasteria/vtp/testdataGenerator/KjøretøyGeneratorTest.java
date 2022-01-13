package no.soprasteria.vtp.testdataGenerator;

import no.soprasteria.vtp.testdataGenerator.identer.FiktiveFnr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KjøretøyGeneratorTest {
    private KjøretøyGenerator kjøretøyGenerator  = new KjøretøyGenerator();
    private FiktiveFnr fiktiveFnr = new FiktiveFnr();

    @Test
    public void testKjøretøyGenerator() {
        var kjøretøy = kjøretøyGenerator.lagFiktivtKjøretøy(fiktiveFnr.tilfeldigMannFnr());
    }

}