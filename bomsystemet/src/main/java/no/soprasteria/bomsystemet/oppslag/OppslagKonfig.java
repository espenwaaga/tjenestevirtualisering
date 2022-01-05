package no.soprasteria.bomsystemet.oppslag;

import java.net.URI;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

import no.soprasteria.bomsystemet.util.http.AbstractConfig;

@ConfigurationProperties(prefix = "oppslag")
public class OppslagKonfig extends AbstractConfig {

    private static final String DEFAULT_PING_PATH = "/";
    private static final String DEFAULT_BASE_URL = "http://veivesenet/api/person";

    @ConstructorBinding
    public OppslagKonfig(@DefaultValue(DEFAULT_PING_PATH) String pingPath,
                             @DefaultValue("true") boolean enabled,
                             @DefaultValue(DEFAULT_BASE_URL) URI baseUri) {
        super(baseUri, pingPath, enabled);
    }
}
