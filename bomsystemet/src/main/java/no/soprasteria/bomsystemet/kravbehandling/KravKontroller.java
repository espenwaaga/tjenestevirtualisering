package no.soprasteria.bomsystemet.kravbehandling;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.soprasteria.bomsystemet.database.Kravregister;
import no.soprasteria.felles.kontrakter.bomsystem.felles.Fødselsnummer;
import no.soprasteria.felles.kontrakter.bomsystem.krav.Krav;

@RestController()
@RequestMapping(KravKontroller.KRAV_PATH)
public class KravKontroller {
    public static final String KRAV_PATH = "/krav";
    private static final String FØDSELSNUMMER_PARAM = "fnr";
    private static final Logger LOG = LoggerFactory.getLogger(KravKontroller.class);

    private final Kravregister kravregister;

    @Autowired
    public KravKontroller(Kravregister kravregister) {
        this.kravregister = kravregister;
    }

    @GetMapping(value = "/{" + FØDSELSNUMMER_PARAM + "}")
    public List<Krav> hentAlleKravPåPerson(@PathVariable(FØDSELSNUMMER_PARAM) Fødselsnummer fødselsnummer) {
        var krav = kravregister.get(fødselsnummer);
        LOG.info("{} har følgende krav registrert på seg {}", fødselsnummer, krav);
        return krav;
    }
}
