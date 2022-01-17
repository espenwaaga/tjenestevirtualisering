package no.soprasteria.felles.kontrakter.bomsystem.krav;

import java.time.LocalDateTime;
import java.util.Objects;

public class Periode {

    private LocalDateTime fra;
    private LocalDateTime tom;

    public Periode(LocalDateTime fra, LocalDateTime tom) {
        this.fra = fra;
        this.tom = tom;
    }

    public LocalDateTime getFra() {
        return fra;
    }

    public void setFra(LocalDateTime fra) {
        this.fra = fra;
    }

    public LocalDateTime getTom() {
        return tom;
    }

    public void setTom(LocalDateTime tom) {
        this.tom = tom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periode periode = (Periode) o;
        return Objects.equals(fra, periode.fra) && Objects.equals(tom, periode.tom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fra, tom);
    }

    @Override
    public String toString() {
        return "Periode{" +
                "fom=" + fra +
                ", tom=" + tom +
                '}';
    }
}
