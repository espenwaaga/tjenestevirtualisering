package no.soprasteria.bomsystemet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BomsystemetApplication {

	public static void main(String[] args) {
		SpringApplication.run(BomsystemetApplication.class, args);
	}

}
