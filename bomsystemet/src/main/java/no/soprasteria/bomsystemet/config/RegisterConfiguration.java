package no.soprasteria.bomsystemet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import no.soprasteria.bomsystemet.database.Forbipasseringsregister;
import no.soprasteria.bomsystemet.database.Kravregister;

@Configuration
public class RegisterConfiguration {

    @Bean
    public Forbipasseringsregister forbipasseringsregister() {
        return new Forbipasseringsregister();
    }

    @Bean
    public Kravregister kravregister() {
        return new Kravregister();
    }
}
