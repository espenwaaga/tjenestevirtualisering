package no.soprasteria.bomsystemet.util.database;

import static java.util.Collections.emptyList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Bompassering;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Bompasseringsinformasjon;
import no.soprasteria.felles.kontrakter.bomsystem.krav.Krav;
import no.soprasteria.felles.kontrakter.bomsystem.krav.Periode;

/**
 * Dette er et register som simulere en database hvor vi lagrer krav opprettet på personer.
 * For enkelhet skyld lagrer vi dette in memory og persistere ikke dette på tvers av kjøringer.
 */
public class Kravregister {

    private final Map<Fødselsnummer, List<Krav>> register = new HashMap<>();

    public void add(Fødselsnummer fødselsnummer, Krav krav) {
        if (!register.containsKey(fødselsnummer)) {
            var kravListe = new ArrayList<Krav>();
            kravListe.add(krav);
            register.put(fødselsnummer, kravListe);
        } else {
            var eksisterendeKrav = register.get(fødselsnummer);
            eksisterendeKrav.add(krav);
        }
    }

    public List<Krav> get(Fødselsnummer fødselsnummer) {
        return Optional.ofNullable(register.get(fødselsnummer)).orElse(emptyList());
    }

    public void remove(Fødselsnummer fødselsnummer) {
        register.remove(fødselsnummer);
    }

    public void remove(Fødselsnummer fødselsnummer, Krav krav) {
        List<Krav> kravene = register.get(fødselsnummer);
        kravene.remove(krav);
    }

    public Krav getEksisterendeKravForPasseringstidspunkt(Fødselsnummer fødselsnummer, LocalDateTime passeringstidspunkt) {
        return get(fødselsnummer).stream()
                .filter(krav -> erPasseringInnenforKrav(passeringstidspunkt, krav))
                .findFirst()
                .orElse(null);
    }


    public void forskyvEksisterendePerioderVedOverlapp(Fødselsnummer fødselsnummer, Krav krav) {
        var gjeldendePeriode = krav.gyldighetsperiode();
        var konflikkrav = getOverlappendeKrav(fødselsnummer, gjeldendePeriode);

        var nyttFastsattKrav = krav;
        while (konflikkrav != null) {
            // Fordel passeringer til korrekt krav
            var bompasseringerForGjeldendeKonflikt = konflikkrav.beregningsgrunnlag().bompasseringer();
            var bompasseringerForGjeldendeKonfliktCopy = new ArrayList<>(bompasseringerForGjeldendeKonflikt);
            for (var bompassering : bompasseringerForGjeldendeKonfliktCopy) {
                if (erPasseringInnenforKrav(bompassering.bompasseringsinformasjon().tidspunkt(), nyttFastsattKrav)) {
                    nyttFastsattKrav.beregningsgrunnlag().bompasseringer().add(bompassering);
                    bompasseringerForGjeldendeKonflikt.remove(bompassering);
                }
            }

            if (bompasseringerForGjeldendeKonflikt.isEmpty()) {
                remove(fødselsnummer, konflikkrav);
                konflikkrav = null;
            } else {
                // Korriger gyldighetsperioden for kravet
                var tidligstFraTidspunkt = bompasseringerForGjeldendeKonflikt.stream()
                        .map(Bompassering::bompasseringsinformasjon)
                        .min(Comparator.comparing(Bompasseringsinformasjon::tidspunkt))
                        .map(Bompasseringsinformasjon::tidspunkt)
                        .orElseThrow();
                konflikkrav.gyldighetsperiode().setFra(tidligstFraTidspunkt);
                konflikkrav.gyldighetsperiode().setTom(tidligstFraTidspunkt.plusHours(1));
            }

            nyttFastsattKrav = konflikkrav;
            konflikkrav = nyttFastsattKrav != null ?  getOverlappendeKrav(fødselsnummer, nyttFastsattKrav.gyldighetsperiode()) : null;
        }
    }


    private Krav getOverlappendeKrav(Fødselsnummer fødselsnummer, Periode gyldighetsperiode) {
        return get(fødselsnummer).stream()
                .filter(k -> ikkeOverlappMedSegSelv(gyldighetsperiode, k))
                .filter(k -> overlapperMedAnnenPeriode(gyldighetsperiode, k))
                .findFirst()
                .orElse(null);
    }

    private boolean overlapperMedAnnenPeriode(Periode gyldighetsperiode, Krav k) {
        return k.gyldighetsperiode().getFra().isEqual(gyldighetsperiode.getTom()) ||
                k.gyldighetsperiode().getFra().isBefore(gyldighetsperiode.getTom());
    }

    private boolean ikkeOverlappMedSegSelv(Periode gyldighetsperiode, Krav k) {
        return !(k.gyldighetsperiode().getFra().isEqual(gyldighetsperiode.getFra()) &&
                k.gyldighetsperiode().getTom().isEqual(gyldighetsperiode.getTom()));
    }


    private boolean erPasseringInnenforKrav(LocalDateTime passeringstidspunkt, Krav krav) {
        return passeringstidspunkt.equals(krav.gyldighetsperiode().getFra()) || passeringstidspunkt.equals(krav.gyldighetsperiode().getTom()) ||
                (passeringstidspunkt.isAfter(krav.gyldighetsperiode().getFra()) && passeringstidspunkt.isBefore(krav.gyldighetsperiode().getTom()));
    }

    @Override
    public String toString() {
        return "Kravregister{" +
                "register=" + register +
                '}';
    }

}
