package no.soprasteria.felles.kontrakter.bomsystem.felles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.constraints.Size;

public record Fødselsnummer(@JsonValue String value) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Fødselsnummer {
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
