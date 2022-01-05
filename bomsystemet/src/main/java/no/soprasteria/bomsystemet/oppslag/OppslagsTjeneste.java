package no.soprasteria.bomsystemet.oppslag;

import static no.soprasteria.bomsystemet.util.http.WebClientConfiguration.OPPSLAG;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import no.soprasteria.felles.bomsystem.Registreringsnummer;
import no.soprasteria.bomsystemet.util.http.AbstractConfig;
import no.soprasteria.bomsystemet.util.http.AbstractWebClientConnection;

@Component
public class OppslagsTjeneste extends AbstractWebClientConnection {

    public OppslagsTjeneste(@Qualifier(OPPSLAG) WebClient webClient, AbstractConfig cfg) {
        super(webClient, cfg);
    }

    // TODO: Mulig erstatt denne med klienten som finnes i felles
    public PersonInformasjon hentPersonInformasjon(Registreringsnummer registreringsnummer) {
        return webClient.get()
                .uri(cfg.getBaseUri())
                .accept(APPLICATION_JSON)
                .retrieve()
                .toEntity(PersonInformasjon.class)
                .block()
                .getBody();
    }

    @Override
    public String name() {
        return null;
    }
}
