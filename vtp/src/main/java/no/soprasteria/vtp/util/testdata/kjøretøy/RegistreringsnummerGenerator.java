package no.soprasteria.vtp.util.testdata.kjøretøy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RegistreringsnummerGenerator {

    private static final Random RANDOM = new Random();
    private static final List<String> registreingsnummer = loadNames("/basedata/registreringsnummer.txt");

    private RegistreringsnummerGenerator() {
    }

    public static String getRandomRegistreringsnummer() {
        if (registreingsnummer.size() <= 0) {
            return null;
        }
        var regnummer = getRandom();
        registreingsnummer.remove(regnummer);
        return regnummer;
    }

    private static String getRandom() {
        return registreingsnummer.get(RANDOM.nextInt(registreingsnummer.size()));
    }

    private static List<String> loadNames(String resourceName) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(RegistreringsnummerGenerator.class.getResourceAsStream(resourceName))))) {
            final List<String> resultat = new ArrayList<>();
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String capitalizedName = strLine.substring(0, 1).toUpperCase() + strLine.substring(1);
                resultat.add(capitalizedName);
            }
            return resultat;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
