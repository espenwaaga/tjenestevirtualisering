package no.soprasteria.felles.kontrakter.bomsystem;

import no.soprasteria.felles.kontrakter.vtp.Registreringsnummer;

public record Forbipassering(Registreringsnummer registreringsnummer,
                             Forbipasseringsinformasjon forbipasseringsinformasjon) {

}
