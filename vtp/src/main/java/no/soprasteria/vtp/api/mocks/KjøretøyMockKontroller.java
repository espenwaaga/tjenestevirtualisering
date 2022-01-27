package no.soprasteria.vtp.api.mocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.soprasteria.felles.kontrakter.bomsystem.felles.Registreringsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.kjøretøy.KjøretøyInfo;
import no.soprasteria.felles.kontrakter.vtp.RegistreringsnummerList;
import no.soprasteria.vtp.register.Kjøretøyregister;

@RestController()
@RequestMapping(KjøretøyMockKontroller.KJØRETØY_PATH)
public class KjøretøyMockKontroller {
    public static final String KJØRETØY_PATH = "/kjøretøy";
    private static final Logger LOG = LoggerFactory.getLogger(KjøretøyMockKontroller.class);

    private final Kjøretøyregister kjøretøyregister;

    @Autowired
    public KjøretøyMockKontroller(Kjøretøyregister kjøretøyregister) {
        this.kjøretøyregister = kjøretøyregister;
    }



    @GetMapping(value = "/{registreringsnummer}")
    public KjøretøyInfo hentKjøretøyInfo(@PathVariable("registreringsnummer") Registreringsnummer registreringsnummer) {
        LOG.info("Henter informasjon om kjøretøy med regnummer [{}]", registreringsnummer);
        return kjøretøyregister.get(registreringsnummer);
    }

    @GetMapping(value = "/registreringsnummerList")
    public RegistreringsnummerList hentRegisterRegistreringsnummerList() {
        LOG.info("Henter alle om kjøretøy");
        return new RegistreringsnummerList(kjøretøyregister.getAlleRegistreringsnummer());
    }
}
