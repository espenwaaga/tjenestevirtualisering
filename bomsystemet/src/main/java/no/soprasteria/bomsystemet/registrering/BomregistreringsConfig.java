package no.soprasteria.bomsystemet.registrering;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "bomregistrering")
public class BomregistreringsConfig {

    private final boolean automatiskKrav;
    private final boolean forbipasseringerIkkeKronologisk;

    @ConstructorBinding
    public BomregistreringsConfig(@DefaultValue("false") boolean automatiskKrav,
                                  @DefaultValue("false") boolean forbipasseringerIkkeKronologisk) {
        this.automatiskKrav = automatiskKrav;
        this.forbipasseringerIkkeKronologisk = forbipasseringerIkkeKronologisk;
    }

    public boolean skalOppretteKravAutomatisk() {
        return automatiskKrav;
    }

    public boolean erFixForforbipasseringerIkkeKronologiskAktivert() {
        return forbipasseringerIkkeKronologisk;
    }
}
