package no.soprasteria.vtp.util.testdata.person;

import no.soprasteria.felles.kontrakter.bomsystem.person.Kjønn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class NavnGenerator {

    private static final Random RANDOM = new Random();
    private static final List<String> etternavn = loadNames("/basedata/etternavn.txt");
    private static final List<String> fornavnKvinner = loadNames("/basedata/fornavn-kvinner.txt");
    private static final List<String> fornavnMenn = loadNames("/basedata/fornavn-menn.txt");

    private NavnGenerator() {
    }

    public static String getRandomFornavnMann() {
        return getRandom(fornavnMenn);
    }

    public static String getRandomFornavnKvinne() {
        return getRandom(fornavnKvinner);
    }

    public static String getRandomFornavn(Kjønn kjønn) {
        return kjønn == Kjønn.K ? getRandomFornavnKvinne() : getRandomFornavnMann();
    }

    public static String getRandomEtternavn() {
        return getRandom(etternavn);
    }

    private static List<String> loadNames(String resourceName) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(NavnGenerator.class.getResourceAsStream(resourceName))))) {
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

    private static String getRandom(List<String> liste) {
        return liste.get(RANDOM.nextInt(liste.size()));
    }

}
