package no.soprasteria.bomsystemet.registrering;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "bomregistrering")
public class BomregistreringsConfig {

    private final boolean automatiskKrav;

    @ConstructorBinding
    public BomregistreringsConfig(@DefaultValue("false") boolean automatiskKrav) {
        this.automatiskKrav = automatiskKrav;
    }

    public boolean skalOppretteKravAutomatisk() {
        return automatiskKrav;
    }
}
