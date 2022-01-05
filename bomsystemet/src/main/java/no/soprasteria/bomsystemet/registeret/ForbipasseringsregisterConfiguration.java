package no.soprasteria.bomsystemet.registeret;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ForbipasseringsregisterConfiguration {

    @Bean
    public Forbipasseringsregister forbipasseringsregister() {
        return new Forbipasseringsregister();
    }

}
