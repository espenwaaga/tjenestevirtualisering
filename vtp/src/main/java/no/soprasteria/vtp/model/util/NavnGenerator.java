package no.soprasteria.vtp.model.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NavnGenerator {

    private static final Random RANDOM = new Random();
    private List<String> etternavn = loadNames("/basedata/etternavn.txt");
    private List<String> fornavnKvinner = loadNames("/basedata/fornavn-kvinner.txt");
    private List<String> fornavnMenn = loadNames("/basedata/fornavn-menn.txt");

    public String getRandomFornavnMann() {
        return getRandom(fornavnMenn);
    }

    public String getRandomFornavnKvinne() {
        return getRandom(fornavnKvinner);
    }

    public String getRandomEtternavn() {
        return getRandom(etternavn);
    }


    private static List<String> loadNames(String resourceName) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(NavnGenerator.class.getResourceAsStream(resourceName)))) {
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

    private static synchronized String getRandom(List<String> liste) {
        return liste.get(RANDOM.nextInt(liste.size()));
    }

}
