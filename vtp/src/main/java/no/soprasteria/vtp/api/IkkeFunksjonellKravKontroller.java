package no.soprasteria.vtp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.soprasteria.felles.kontrakter.vtp.IkkeFunksjonelleKrav;
import no.soprasteria.vtp.config.KontrollerKonfig;

@RestController()
@RequestMapping(IkkeFunksjonellKravKontroller.TEKNISK_PATH)
public class IkkeFunksjonellKravKontroller {
    private static final Logger LOG = LoggerFactory.getLogger(IkkeFunksjonellKravKontroller.class);
    protected static final String TEKNISK_PATH = "/teknisk";

    private final KontrollerKonfig kontrolleKonfig;

    @Autowired
    public IkkeFunksjonellKravKontroller(KontrollerKonfig kontrolleKonfig) {
        this.kontrolleKonfig = kontrolleKonfig;
    }

    @PostMapping
    public boolean simulerIkkeFunksjonelleKrav(@RequestBody IkkeFunksjonelleKrav ikkeFunksjonelleKrav) {
        var delay = ikkeFunksjonelleKrav.delay();
        kontrolleKonfig.setDelayVeivesenet(delay);
        kontrolleKonfig.setDelaySkatteetaten(delay);
        LOG.info("Ny kontrollerkonfig er nå {}", kontrolleKonfig);
        return true;
    }

}
