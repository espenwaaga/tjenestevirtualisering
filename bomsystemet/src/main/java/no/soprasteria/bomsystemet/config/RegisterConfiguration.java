package no.soprasteria.bomsystemet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import no.soprasteria.bomsystemet.register.Forbipasseringsregister;

@Configuration
public class RegisterConfiguration {

    @Bean
    public Forbipasseringsregister forbipasseringsregister() {
        return new Forbipasseringsregister();
    }

}
