package no.soprasteria.felles.kontrakter.bomsystem.krav;

import no.soprasteria.felles.kontrakter.bomsystem.person.Person;

public record Krav(Person person,
                   Periode gyldighetsperiode,
                   Grunnlag beregningsgrunnlag,
                   int avgift) {

}
