package no.soprasteria.felles.kontrakter.vtp;

import java.util.ArrayList;
import java.util.List;

public class TestdataBestilling {

    private List<Integer> testdataPersonList = new ArrayList<>();

    public void leggTilEnTestpersonBestilling(int antallBilder) {
        testdataPersonList.add(antallBilder);
    }

    public int getAntallPersoner() {
        return testdataPersonList.size();
    }

    public List<Integer> getTestdataPersonLis() {
        return testdataPersonList;
    }}
