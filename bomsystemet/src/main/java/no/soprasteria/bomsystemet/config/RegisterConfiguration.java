package no.soprasteria.bomsystemet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import no.soprasteria.bomsystemet.database.Forbipasseringsregister;

@Configuration
public class RegisterConfiguration {

    @Bean
    public Forbipasseringsregister forbipasseringsregister() {
        return new Forbipasseringsregister();
    }

}
