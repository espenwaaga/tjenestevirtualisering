package no.soprasteria.vtp.testdataGenerator.identer;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.person.Kjønn;
import no.soprasteria.vtp.testdataGenerator.util.FødselsdagGenerator;

/**
 * Hent et tilfeldig gyldig men fiktivt Fødselsnummer.
 *
 */
public final class FnrGenerator {

    private FnrGenerator() {
    }

    /** Returnerer FNR for mann > 18 år */
    public static Fødselsnummer tilfeldigMannFnr() {
        return new FoedselsnummerGenerator
                .FodselsnummerGeneratorBuilder()
                .kjonn(Kjønn.M)
                .fodselsdato(FødselsdagGenerator.genererEnGyldigRandomFødselsdag())
                .buildAndGenerate();
    }

    /** Returnerer FNR for kvinne > 18 år */
    public static Fødselsnummer tilfeldigKvinneFnr() {
        return new FoedselsnummerGenerator
                .FodselsnummerGeneratorBuilder()
                .kjonn(Kjønn.K)
                .fodselsdato(FødselsdagGenerator.genererEnGyldigRandomFødselsdag())
                .buildAndGenerate();
    }

    public static Fødselsnummer tilfedigFnr(Kjønn kjønn) {
        return kjønn == Kjønn.K ? tilfeldigKvinneFnr() : tilfeldigMannFnr();
    }
}
