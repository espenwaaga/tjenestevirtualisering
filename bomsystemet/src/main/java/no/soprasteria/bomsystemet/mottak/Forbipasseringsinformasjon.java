package no.soprasteria.bomsystemet.mottak;

import java.time.LocalDate;

public record Forbipasseringsinformasjon(LocalDate tidspunkt, Sone sone) {
}
