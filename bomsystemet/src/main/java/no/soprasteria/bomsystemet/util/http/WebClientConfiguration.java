package no.soprasteria.bomsystemet.util.http;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import no.soprasteria.bomsystemet.oppslag.OppslagKonfig;


@Configuration
public class WebClientConfiguration {

    public static final String OPPSLAG = "OPPSLAG";

    @Bean
    @Qualifier(OPPSLAG)
    public WebClient webClientOppslag(Builder builder, OppslagKonfig cfg) {
        return builder
                .baseUrl(cfg.getBaseUri().toString())
                .build();
    }
}
