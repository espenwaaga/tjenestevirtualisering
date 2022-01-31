package no.soprasteria.vtp.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KontrollerKonfigurasjon {

    @Bean
    public KontrollerKonfig kontrollerKonfig() {
        return new KontrollerKonfig(0, 0);
    }
}
