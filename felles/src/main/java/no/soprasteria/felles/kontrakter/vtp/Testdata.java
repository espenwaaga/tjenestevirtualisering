package no.soprasteria.felles.kontrakter.vtp;

import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Kjøretøy;
import no.soprasteria.felles.kontrakter.bomsystem.person.Person;

import java.util.List;

public record Testdata(List<Person> person, List<Kjøretøy> kjøretøy) {
}
