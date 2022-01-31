package no.soprasteria.vtp.util.config;

public class KontrollerKonfig {
    private int delayVeivesenet;
    private int delaySkatteetaten;

    public KontrollerKonfig(int delayVeivesenet, int delaySkatteetaten) {
        this.delayVeivesenet = delayVeivesenet;
        this.delaySkatteetaten = delaySkatteetaten;
    }

    public int delayVeivesenet() {
        return delayVeivesenet;
    }

    public void setDelayVeivesenet(int delayVeivesenet) {
        this.delayVeivesenet = delayVeivesenet;
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
                "delayVeivesenet=" + delayVeivesenet +
                ", delaySkatteetaten=" + delaySkatteetaten +
                '}';
    }
}
