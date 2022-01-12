package no.soprasteria.vtp.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class TestPersonGenerator {

    @Test
    public void testLagTestdata() throws IOException {
        PersonGenerator personGenerator = new PersonGenerator();
        personGenerator.lagFiktivPerson();

    }


}