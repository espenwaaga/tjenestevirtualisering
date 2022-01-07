package no.soprasteria.felles.kontrakter.bomsystem.forbipassering;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;

public record Forbipassering(Registreringsnummer registreringsnummer,
                             Forbipasseringsinformasjon forbipasseringsinformasjon) {

}
