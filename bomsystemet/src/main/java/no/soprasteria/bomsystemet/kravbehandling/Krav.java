package no.soprasteria.bomsystemet.kravbehandling;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;

public record Krav(Fødselsnummer fnr, String epost, int avgift) {
}
