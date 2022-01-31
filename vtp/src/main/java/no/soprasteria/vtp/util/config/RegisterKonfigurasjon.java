package no.soprasteria.vtp.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import no.soprasteria.vtp.register.Kjøretøyregister;
import no.soprasteria.vtp.register.Personregister;

@Configuration
public class RegisterKonfigurasjon {

    @Bean
    public Kjøretøyregister kjøretøyregister() {
        return new Kjøretøyregister();
    }

    @Bean
    public Personregister personregister() {
        return new Personregister();
    }
}
