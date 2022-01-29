package no.soprasteria.felles.kontrakter.vtp;

import java.util.List;
import java.util.Map;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Kjøretøy;
import no.soprasteria.felles.kontrakter.bomsystem.person.Person;

public record TestdataLogisk(Map<Person, List<Kjøretøy>> testdata) {
//public record TestdataLogisk(Map<Fødselsnummer, Testdata> testdata) {
}
