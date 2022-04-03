package no.soprasteria.felles.kontrakter.bomsystem.felles;

import javax.validation.constraints.Max;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record Registreringsnummer(@JsonValue @Max(value = 7) String value) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Registreringsnummer {
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
