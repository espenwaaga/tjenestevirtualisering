package no.soprasteria.felles.kontrakter.bomsystem.forbipassering;

import java.time.LocalDate;

public record Forbipasseringsinformasjon(LocalDate tidspunkt, Sone sone) {
}
