package no.soprasteria.bomsystemet.registrering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import no.soprasteria.bomsystemet.util.database.Bompasseringsregister;
import no.soprasteria.bomsystemet.kravbehandling.Kravbehandler;
import no.soprasteria.bomsystemet.oppslag.KjøretøyOppslagKlient;
import no.soprasteria.felles.kontrakter.bomsystem.bompassering.Bompassering;

@RestController()
@RequestMapping(BomregistreringKontroller.MOTTAK_PATH)
public class BomregistreringKontroller {
    public static final String MOTTAK_PATH = "/mottak";
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringKontroller.class);

    private final BomregistreringsConfig config;
    private final KjøretøyOppslagKlient kjøretøyOppslag;
    private final Bompasseringsregister bompasseringsregister;
    private final Kravbehandler kravbehandler;

    @Autowired
    public BomregistreringKontroller(BomregistreringsConfig config,
                                     KjøretøyOppslagKlient kjøretøyOppslag,
                                     Bompasseringsregister bompasseringsregister,
                                     Kravbehandler kravbehandler) {
        this.config = config;
        this.kjøretøyOppslag = kjøretøyOppslag;
        this.bompasseringsregister = bompasseringsregister;
        this.kravbehandler = kravbehandler;
    }

    @PostMapping
    public Boolean registrerBompassering(@RequestBody Bompassering bompassering) {
        if (!kjøretøyOppslag.finnesKjøretøyIRegister(bompassering.registreringsnummer())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, bompassering.registreringsnummer().value() + " finnes ikke i kjøretøyregisteret!");
        }
        leggBompasseringIRegister(bompassering);

        if (config.skalOppretteKravAutomatisk()) {
            opprettKrav(bompassering);
        }
        return true;
    }

    private void leggBompasseringIRegister(Bompassering bompassering) {
        LOG.info("Registerer passering for {}", bompassering.registreringsnummer());
        bompasseringsregister.add(bompassering.registreringsnummer(), bompassering.bompasseringsinformasjon());
        LOG.info("Registertet: {}", bompasseringsregister);
    }

    private void opprettKrav(Bompassering bompassering) {
        if (config.erFixForBompasseringerIkkeKronologiskAktivert()) {
            kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(bompassering);
        } else {
            kravbehandler.opprettKravPåPassering(bompassering);
        }
    }
}
