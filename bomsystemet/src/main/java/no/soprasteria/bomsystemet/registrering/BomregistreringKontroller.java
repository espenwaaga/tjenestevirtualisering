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

import no.soprasteria.bomsystemet.database.Forbipasseringsregister;
import no.soprasteria.bomsystemet.kravbehandling.Kravbehandler;
import no.soprasteria.bomsystemet.oppslag.kjøretøy.KjøretøyOppslagKlient;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipassering;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.ForbipasseringList;

@RestController()
@RequestMapping(BomregistreringKontroller.MOTTAK_PATH)
public class BomregistreringKontroller {
    public static final String MOTTAK_PATH = "/mottak";
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringKontroller.class);

    private final BomregistreringsConfig config;
    private final KjøretøyOppslagKlient kjøretøyOppslag;
    private final Forbipasseringsregister forbipasseringsregister;
    private final Kravbehandler kravbehandler;

    @Autowired
    public BomregistreringKontroller(BomregistreringsConfig config,
                                     KjøretøyOppslagKlient kjøretøyOppslag,
                                     Forbipasseringsregister forbipasseringsregister,
                                     Kravbehandler kravbehandler) {
        this.config = config;
        this.kjøretøyOppslag = kjøretøyOppslag;
        this.forbipasseringsregister = forbipasseringsregister;
        this.kravbehandler = kravbehandler;
    }

    @PostMapping
    public Boolean registrerForbipassering(@RequestBody Forbipassering forbipassering) {
        if (!kjøretøyOppslag.finnesKjøretøyIRegister(forbipassering.registreringsnummer())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, forbipassering.registreringsnummer().value() + " finnes ikke i kjøretøyregisteret!");
        }
        leggForbipasseringIRegister(forbipassering);

        if (config.skalOppretteKravAutomatisk()) {
            if (config.erFixForforbipasseringerIkkeKronologiskAktivert()) {
                kravbehandler.opprettKravPåPasseringMedSafeGuardForForsinketRegistrering(forbipassering);
            } else {
                kravbehandler.opprettKravPåPassering(forbipassering);
            }
        }
        return true;
    }

    @PostMapping(value = "/list")
    public void registrerForbipassering(@RequestBody ForbipasseringList forbipasseringList) {
        forbipasseringList.forbipasseringList().forEach(this::leggForbipasseringIRegister);
    }

    private void leggForbipasseringIRegister(Forbipassering forbipassering) {
        LOG.info("Registerer passering for {}", forbipassering.registreringsnummer());
        forbipasseringsregister.add(forbipassering.registreringsnummer(), forbipassering.forbipasseringsinformasjon());
        LOG.info("Registertet: {}", forbipasseringsregister);
    }
}
