package no.soprasteria.felles.bomsystem;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record Registreringsnummer(@JsonValue String value) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Registreringsnummer {
        // Legg til validering av Registeringsnummer
    }

    @Override
    public String value() {
        return value;
    }
}
