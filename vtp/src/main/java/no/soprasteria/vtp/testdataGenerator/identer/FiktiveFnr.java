package no.soprasteria.vtp.testdataGenerator.identer;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.person.Kjønn;
import no.soprasteria.vtp.testdataGenerator.util.FødselsdagGenerator;

/**
 * Hent et tilfeldig gyldig men fiktivt Fødselsnummer.
 *
 */
public class FiktiveFnr {

    /** Returnerer FNR for mann > 18 år */
    public Fødselsnummer tilfeldigMannFnr() {
        return new FoedselsnummerGenerator
                .FodselsnummerGeneratorBuilder()
                .kjonn(Kjønn.M)
                .fodselsdato(FødselsdagGenerator.genererEnGyldigRandomFødselsdag())
                .buildAndGenerate();
    }

    /** Returnerer FNR for kvinne > 18 år */
    public Fødselsnummer tilfeldigKvinneFnr() {
        return new FoedselsnummerGenerator
                .FodselsnummerGeneratorBuilder()
                .kjonn(Kjønn.K)
                .fodselsdato(FødselsdagGenerator.genererEnGyldigRandomFødselsdag())
                .buildAndGenerate();
    }
}
