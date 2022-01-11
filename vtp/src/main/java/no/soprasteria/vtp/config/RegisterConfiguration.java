package no.soprasteria.vtp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import no.soprasteria.vtp.register.Kjøretøyregister;
import no.soprasteria.vtp.register.Personregister;

@Configuration
public class RegisterConfiguration {

    @Bean
    public Kjøretøyregister kjøretøyregister() {
        return new Kjøretøyregister();
    }

    @Bean
    public Personregister personregister() {
        return new Personregister();
    }
}
