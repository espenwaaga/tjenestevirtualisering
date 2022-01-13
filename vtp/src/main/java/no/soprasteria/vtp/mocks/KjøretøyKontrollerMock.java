package no.soprasteria.vtp.mocks;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.Kjøretøy;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;
import no.soprasteria.felles.kontrakter.vtp.RegistreringsnummerList;
import no.soprasteria.vtp.register.Kjøretøyregister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping(KjøretøyKontrollerMock.KJØRETØY_PATH)
public class KjøretøyKontrollerMock {
    public static final String KJØRETØY_PATH = "/kjøretøy";
    private static final Logger LOG = LoggerFactory.getLogger(KjøretøyKontrollerMock.class);

    private final Kjøretøyregister kjøretøyregister;

    @Autowired
    public KjøretøyKontrollerMock(Kjøretøyregister kjøretøyregister) {
        this.kjøretøyregister = kjøretøyregister;
    }

    @PostMapping
    public void registrerKjøretøy(@RequestBody Kjøretøy kjøretøy) {
        LOG.info("Registrert bil med registernummer {}", kjøretøy.registreringsnummer().value());
        kjøretøyregister.add(kjøretøy.registreringsnummer(), kjøretøy.kjøretøyInfo());
        LOG.info("Registernummer i register: {}", kjøretøyregister);
        // TODO: Registere også person i personregisteret
    }

    @GetMapping(value = "/{registreringsnummer}")
    public KjøretøyInfo hentKjøretøyInfo(@PathVariable("registreringsnummer") Registreringsnummer registreringsnummer) {
        LOG.info("Henter informasjon om kjøretøy med regnummer [{}]", registreringsnummer.value());
        var kjøretøyInfo = kjøretøyregister.get(registreringsnummer);
        if (kjøretøyInfo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fant ikke kjøretøy registert med registereringsnummer " + registreringsnummer.value());
        }
        return kjøretøyInfo;
    }

    @GetMapping()
    public RegistreringsnummerList hentKjøretøy() {
        LOG.info("Henter alle om kjøretøy");
        return new RegistreringsnummerList(kjøretøyregister.getAlleRegistreringsnummer());
    }
}
