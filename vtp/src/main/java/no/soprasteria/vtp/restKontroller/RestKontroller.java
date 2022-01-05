package no.soprasteria.vtp.restKontroller;

import no.soprasteria.felles.kontrakter.vtp.Kjøretøy;
import no.soprasteria.felles.kontrakter.vtp.KjøretøyInfo;
import no.soprasteria.felles.kontrakter.vtp.Registreringsnummer;
import no.soprasteria.vtp.registeret.Kjøretøyregister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
public class RestKontroller {
    public static final String KJØRETØY_PATH = "/kjøretøy";
    private static final Logger LOG = LoggerFactory.getLogger(RestKontroller.class);

    private final Kjøretøyregister kjøretøyregister;

    @Autowired
    public RestKontroller(Kjøretøyregister kjøretøyregister) {
        this.kjøretøyregister = kjøretøyregister;
    }

    @PostMapping(value = KJØRETØY_PATH)
    public void registrerKjøretøy(@RequestBody Kjøretøy kjøretøy) {
        LOG.info("Registrert bil med registernummer {}",kjøretøy.registreringsnummer());
        kjøretøyregister.add(kjøretøy.registreringsnummer(), kjøretøy.kjøretøyInfo());
        LOG.info("Registernummer i register: {}", kjøretøyregister);
    }

    @GetMapping(value = KJØRETØY_PATH + "/{registreringsnummer}")
    public KjøretøyInfo hentKjøretøyInfo(@PathVariable("registreringsnummer") Registreringsnummer registreringsnummer) {
        LOG.info("Henter info for: {}", registreringsnummer);
        return kjøretøyregister.get(registreringsnummer);
    }
}
