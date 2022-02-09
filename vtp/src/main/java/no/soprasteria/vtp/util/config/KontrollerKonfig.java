package no.soprasteria.vtp.util.config;

public class KontrollerKonfig {
    private int delayVegvesen;
    private int delaySkatteetaten;

    public KontrollerKonfig(int delayVegvesen, int delaySkatteetaten) {
        this.delayVegvesen = delayVegvesen;
        this.delaySkatteetaten = delaySkatteetaten;
    }

    public int delayVegvesen() {
        return delayVegvesen;
    }

    public void setDelayVegvesen(int delayVegvesen) {
        this.delayVegvesen = delayVegvesen;
    }

    public int delaySkatteetaten() {
        return delaySkatteetaten;
    }

    public void setDelaySkatteetaten(int delaySkatteetaten) {
        this.delaySkatteetaten = delaySkatteetaten;
    }

    @Override
    public String toString() {
        return "KontrollerKonfig{" +
                "delayVegvesen=" + delayVegvesen +
                ", delaySkatteetaten=" + delaySkatteetaten +
                '}';
    }
}
