package no.soprasteria.bomsystemet.registrering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.soprasteria.bomsystemet.database.Forbipasseringsregister;
import no.soprasteria.bomsystemet.kravbehandling.Kravbehandler;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.Forbipassering;
import no.soprasteria.felles.kontrakter.bomsystem.forbipassering.ForbipasseringList;

@RestController()
@RequestMapping(BomregistreringKontroller.MOTTAK_PATH)
public class BomregistreringKontroller {
    public static final String MOTTAK_PATH = "/mottak";
    private static final Logger LOG = LoggerFactory.getLogger(BomregistreringKontroller.class);

    private final Forbipasseringsregister forbipasseringsregister;
    private final Kravbehandler kravbehandler;

    @Autowired
    public BomregistreringKontroller(Forbipasseringsregister forbipasseringsregister, Kravbehandler kravbehandler) {
        this.forbipasseringsregister = forbipasseringsregister;
        this.kravbehandler = kravbehandler;
    }

    @PostMapping
    public void registrerForbipassering(@RequestBody Forbipassering forbipassering) {
        leggForbipasseringIRegister(forbipassering);
        kravbehandler.opprettKravPåPassering(forbipassering);
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
