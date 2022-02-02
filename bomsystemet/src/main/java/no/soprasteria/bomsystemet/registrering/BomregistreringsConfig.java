package no.soprasteria.bomsystemet.registrering;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "bomregistrering")
public class BomregistreringsConfig {

    private final boolean automatiskKrav;
    private final boolean bompasseringerIkkeKronologiskFikset;

    @ConstructorBinding
    public BomregistreringsConfig(@DefaultValue("false") boolean automatiskKrav,
                                  @DefaultValue("false") boolean bompasseringerIkkeKronologiskFikset) {
        this.automatiskKrav = automatiskKrav;
        this.bompasseringerIkkeKronologiskFikset = bompasseringerIkkeKronologiskFikset;
    }

    public boolean skalOppretteKravAutomatisk() {
        return automatiskKrav;
    }

    public boolean erFixForBompasseringerIkkeKronologiskAktivert() {
        return bompasseringerIkkeKronologiskFikset;
    }
}
