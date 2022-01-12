package no.soprasteria.felles.kontrakter.bomsystem.person;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;

public record Person(Navn navn, Fødselsnummer fnr, Adresse adresse, String epost) {


}
