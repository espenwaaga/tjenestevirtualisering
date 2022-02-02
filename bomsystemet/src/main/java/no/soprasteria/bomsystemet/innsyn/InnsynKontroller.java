package no.soprasteria.bomsystemet.innsyn;

import static no.soprasteria.bomsystemet.innsyn.InnsynKontroller.INNSYN_API_PATH;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.soprasteria.bomsystemet.util.database.Bompasseringsregister;
import no.soprasteria.bomsystemet.util.database.Kravregister;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.krav.Krav;

@RestController()
@RequestMapping(INNSYN_API_PATH)
public class InnsynKontroller {
    private static final Logger LOG = LoggerFactory.getLogger(InnsynKontroller.class);
    public static final String INNSYN_API_PATH = "/innsyn";
    public static final String KRAV_PATH = "/krav";
    private static final String FØDSELSNUMMER_PARAM = "fnr";

    private final Kravregister kravregister;
    private final Bompasseringsregister bompasseringsregister;

    @Autowired
    public InnsynKontroller(Kravregister kravregister, Bompasseringsregister bompasseringsregister) {
        this.kravregister = kravregister;
        this.bompasseringsregister = bompasseringsregister;
    }

    @GetMapping(value = KRAV_PATH + "/{" + FØDSELSNUMMER_PARAM + "}") // path='/krav/{fnr}'
    public List<Krav> hentAlleKravPåPerson(@PathVariable(FØDSELSNUMMER_PARAM) Fødselsnummer fødselsnummer) {
        var krav = kravregister.get(fødselsnummer);
        LOG.info("{} har følgende krav registrert på seg {}", fødselsnummer, krav);
        return krav;
    }
}
