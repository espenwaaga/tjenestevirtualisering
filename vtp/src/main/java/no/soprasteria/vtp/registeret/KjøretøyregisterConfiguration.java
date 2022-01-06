package no.soprasteria.vtp.registeret;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KjøretøyregisterConfiguration {

    @Bean
    public Kjøretøyregister kjøretøyregister() {
        return new Kjøretøyregister();
    }
}
