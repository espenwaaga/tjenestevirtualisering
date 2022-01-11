package no.soprasteria.felles.kontrakter.bomsystem.vtp;

import java.util.List;

import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Kjøretøy;
import no.soprasteria.felles.kontrakter.bomsystem.person.PersonInformasjon;

public record TestscenarioData(PersonInformasjon personInformasjon, List<Kjøretøy> kjøretøy) {

    public TestscenarioData lag()
}
