package no.soprasteria.vtp.mocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;
import no.soprasteria.vtp.register.Kjøretøyregister;

@RestController()
@RequestMapping(KjøretøyMockKontroller.KJØRETØY_PATH)
public class KjøretøyMockKontroller {
    private static final Logger LOG = LoggerFactory.getLogger(KjøretøyMockKontroller.class);
    private static final String REGISTRERINGSNUMMER_PATH_PARAM = "registreringsnummer";
    protected static final String KJØRETØY_PATH = "/kjøretøy";

    private final Kjøretøyregister kjøretøyregister;

    @Autowired
    public KjøretøyMockKontroller(Kjøretøyregister kjøretøyregister) {
        this.kjøretøyregister = kjøretøyregister;
    }

    /**
     * Svarer på en GET mot følgende endepunkt http://localhost:8060/api/kjøretøy/{SV123456}
     * Endepunktet slår opp kjøretøyet i registeret og returnerer informasjon om dette
     * @param registreringsnummer
     */
    @GetMapping(value = "/{" + REGISTRERINGSNUMMER_PATH_PARAM + "}")
    public KjøretøyInfo hentKjøretøyInfo(@PathVariable(REGISTRERINGSNUMMER_PATH_PARAM) Registreringsnummer registreringsnummer) {
        LOG.info("Henter informasjon om kjøretøy med regnummer [{}]", registreringsnummer);
        return kjøretøyregister.get(registreringsnummer);
    }

}
