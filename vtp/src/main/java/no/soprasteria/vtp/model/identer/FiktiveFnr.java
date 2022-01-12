package no.soprasteria.vtp.model.identer;

import no.soprasteria.vtp.model.enums.IdentType;
import no.soprasteria.felles.kontrakter.bomsystem.person.Kjønn;
import no.soprasteria.vtp.model.util.TestdataUtil;

/**
 * Hent et tilfeldig gyldig men fiktivt Fødselsnummer.
 *
 */
public class FiktiveFnr {

    /** Returnerer FNR for mann > 18 år */
    public String tilfeldigMannFnr() {
        return new FoedselsnummerGenerator
                .FodselsnummerGeneratorBuilder()
                .kjonn(Kjønn.M)
                .fodselsdato(TestdataUtil.generateRandomPlausibleBirtdayParent())
                .buildAndGenerate();
    }

    /** Returnerer FNR for kvinne > 18 år */
    public String tilfeldigKvinneFnr() {
        return new FoedselsnummerGenerator
                .FodselsnummerGeneratorBuilder()
                .kjonn(Kjønn.K)
                .fodselsdato(TestdataUtil.generateRandomPlausibleBirtdayParent())
                .buildAndGenerate();
    }

    /** Returnerer FNR for barn (tilfeldig kjønn) < 3 år */
    public String tilfeldigBarnUnderTreAarFnr() {
        return new FoedselsnummerGenerator
                .FodselsnummerGeneratorBuilder()
                .fodselsdato(TestdataUtil.generateBirthdateNowMinusThreeYears())
                .buildAndGenerate();
    }

    /** Returnerer DNR for kvinne > 18 år */
    public String tilfeldigKvinneDnr() {
        return new FoedselsnummerGenerator
                .FodselsnummerGeneratorBuilder()
                .fodselsdato(TestdataUtil.generateRandomPlausibleBirtdayParent())
                .identType(IdentType.DNR)
                .buildAndGenerate();
    }


}
