package no.soprasteria.vtp.util.testdata.person;

import no.soprasteria.felles.kontrakter.bomsystem.person.Adresse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static no.soprasteria.felles.http.JacksonObjectMapper.mapper;

public class AdresseGenerator {
    private static final String resourceName = "/basedata/adresse-maler.json";
    private static final Random RANDOM = new Random();

    private static final List<Adresse>  adresseList = loadAdresser();

    private AdresseGenerator()  {
    }

    public static Adresse getRandomAdresse() {
        return adresseList.get(RANDOM.nextInt(adresseList.size()));
    }

    private static List<Adresse> loadAdresser() {
        try (InputStream is = AdresseGenerator.class.getResourceAsStream(resourceName)) {
            return Arrays.asList(mapper.readValue(is, Adresse[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
