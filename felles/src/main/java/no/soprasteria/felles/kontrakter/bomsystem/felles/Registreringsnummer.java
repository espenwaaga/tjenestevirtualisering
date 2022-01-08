package no.soprasteria.felles.kontrakter.bomsystem.felles;

import javax.validation.Valid;
import javax.validation.constraints.Max;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record Registreringsnummer(@JsonValue String value) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Valid
    @Max(value = 7)
    public Registreringsnummer {
        //TODO: Legg til validering av Registeringsnummer
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value();
    }
}
