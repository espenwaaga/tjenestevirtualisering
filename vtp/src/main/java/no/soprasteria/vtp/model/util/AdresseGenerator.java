package no.soprasteria.vtp.model.util;

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

    private final List<Adresse>  adresseList = loadAdresser();

    public AdresseGenerator()  {
    }

    public Adresse getRandomAdresse() {
        return adresseList.get(RANDOM.nextInt(adresseList.size()));
    }

    private List<Adresse> loadAdresser() {
        try (InputStream is = getClass().getResourceAsStream(resourceName)) {
            return Arrays.asList(mapper.readValue(is, Adresse[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
