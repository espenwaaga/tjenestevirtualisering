package no.soprasteria.felles.kontrakter.bomsystem.bompassering;

import java.time.LocalDateTime;

public record Bompasseringsinformasjon(LocalDateTime tidspunkt, Sone sone) {
}
