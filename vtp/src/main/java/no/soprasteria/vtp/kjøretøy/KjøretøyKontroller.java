package no.soprasteria.vtp.kjøretøy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.soprasteria.felles.kontrakter.vtp.Kjøretøy;
import no.soprasteria.felles.kontrakter.vtp.KjøretøyInfo;
import no.soprasteria.felles.kontrakter.vtp.Registreringsnummer;
import no.soprasteria.vtp.registeret.Kjøretøyregister;

@RestController()
@RequestMapping(KjøretøyKontroller.KJØRETØY_PATH)
public class KjøretøyKontroller {
    public static final String KJØRETØY_PATH = "/kjøretøy";
    private static final Logger LOG = LoggerFactory.getLogger(KjøretøyKontroller.class);

    private final Kjøretøyregister kjøretøyregister;

    @Autowired
    public KjøretøyKontroller(Kjøretøyregister kjøretøyregister) {
        this.kjøretøyregister = kjøretøyregister;
    }

    @PostMapping()
    public void registrerKjøretøy(@RequestBody Kjøretøy kjøretøy) {
        LOG.info("Registrert bil med registernummer {}",kjøretøy.registreringsnummer());
        kjøretøyregister.add(kjøretøy.registreringsnummer(), kjøretøy.kjøretøyInfo());
        LOG.info("Registernummer i register: {}", kjøretøyregister);
    }

    @GetMapping(value = "/{registreringsnummer}")
    public KjøretøyInfo hentKjøretøyInfo(@PathVariable("registreringsnummer") Registreringsnummer registreringsnummer) {
        LOG.info("Henter info for: {}", registreringsnummer);
        return kjøretøyregister.get(registreringsnummer);
    }
}
