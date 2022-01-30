package no.soprasteria.felles.kontrakter.vtp;

import java.util.List;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Kjøretøy;
import no.soprasteria.felles.kontrakter.bomsystem.person.Person;

public record Testperson(Person person, List<Kjøretøy> kjøretøyene) {

    public Fødselsnummer fnr() {
        return person.fnr();
    }

    public Kjøretøy kjøretøy() {
        guardFlereRegistreteKjøretøy();
        return kjøretøyene.get(0);
    }

    public List<Registreringsnummer> registreringnummereForAlleRegistreteKjøretøy() {
        return kjøretøyene.stream()
                .map(Kjøretøy::registreringsnummer)
                .toList();
    }

    private void guardFlereRegistreteKjøretøy() {
        if (kjøretøyene == null) {
            throw new RuntimeException("Det er ingen kjøretøy registret på person " + fnr());
        }
        if (kjøretøyene.size() > 1) {
            throw new RuntimeException("Det er mer enn ett kjøretøy registrert på person! Bruk metoden kjøretøyene() og spesifiser hvilket kjøretøy du vil bruke");
        }
    }
}
