package no.soprasteria.bomsystemet.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import no.soprasteria.bomsystemet.util.database.Bompasseringsregister;
import no.soprasteria.bomsystemet.util.database.Kravregister;

@Configuration
public class RegisterConfiguration {

    @Bean
    public Bompasseringsregister bompasseringsregister() {
        return new Bompasseringsregister();
    }

    @Bean
    public Kravregister kravregister() {
        return new Kravregister();
    }
}
