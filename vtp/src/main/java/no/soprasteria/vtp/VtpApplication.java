package no.soprasteria.vtp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class VtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(VtpApplication.class, args);
	}

}
