package no.soprasteria.vtp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.soprasteria.vtp.util.config.KontrollerKonfig;

@RestController()
@RequestMapping(SimulerIkkeFunksjonelleKravKontroller.SIMULER_IKKE_FUNKSJONELLE_KRAV_PATH)
public class SimulerIkkeFunksjonelleKravKontroller {
    protected static final String SIMULER_IKKE_FUNKSJONELLE_KRAV_PATH = "/teknisk";
    private static final Logger LOG = LoggerFactory.getLogger(SimulerIkkeFunksjonelleKravKontroller.class);
    private static final int DEFAULT_DELAY_MS = 0;

    private final KontrollerKonfig kontrolleKonfig;

    @Autowired
    public SimulerIkkeFunksjonelleKravKontroller(KontrollerKonfig kontrolleKonfig) {
        this.kontrolleKonfig = kontrolleKonfig;
    }

    @PostMapping
    public boolean simulerDelayForMockene(@RequestBody int delay) {
        kontrolleKonfig.setDelayVegvesen(delay);
        kontrolleKonfig.setDelaySkatteetaten(delay);
        LOG.info("Forsinkelsen på mockene er nå endret til {}", kontrolleKonfig);
        return true;
    }

    @PostMapping(path = "/reset")
    public boolean resetTilDefault() {
        kontrolleKonfig.setDelayVegvesen(DEFAULT_DELAY_MS);
        kontrolleKonfig.setDelaySkatteetaten(DEFAULT_DELAY_MS);
        LOG.info("Konfig for simulering av ikke-funksjonelle krav API er satt tilbake til default: {}", kontrolleKonfig);
        return true;
    }

}
