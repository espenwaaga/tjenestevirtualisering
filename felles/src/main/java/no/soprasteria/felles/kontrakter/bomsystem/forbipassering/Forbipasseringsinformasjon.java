package no.soprasteria.felles.kontrakter.bomsystem.forbipassering;

import java.time.LocalDateTime;

public record Forbipasseringsinformasjon(LocalDateTime tidspunkt, Sone sone) {
}
