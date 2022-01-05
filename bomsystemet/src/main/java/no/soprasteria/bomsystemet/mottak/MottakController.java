package no.soprasteria.bomsystemet.mottak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.soprasteria.bomsystemet.registeret.Forbipasseringsregister;
import no.soprasteria.felles.bomsystem.Forbipassering;

@RestController()
@RequestMapping(MottakController.MOTTAK_PATH)
public class MottakController {
    private static final Logger LOG = LoggerFactory.getLogger(MottakController.class);
    public static final String MOTTAK_PATH = "/mottak";

    private final Forbipasseringsregister forbipasseringsregister;

    @Autowired
    public MottakController(Forbipasseringsregister forbipasseringsregister) {
        this.forbipasseringsregister = forbipasseringsregister;
    }

    @PostMapping()
    public void registrerForbipassering(@RequestBody Forbipassering forbipassering) {
        LOG.info("Registerer passering for {}", forbipassering.registreringsnummer());
        forbipasseringsregister.add(forbipassering.registreringsnummer(), forbipassering.forbipasseringsinformasjon());
        LOG.info("Registertet: {}", forbipasseringsregister);
    }
}
