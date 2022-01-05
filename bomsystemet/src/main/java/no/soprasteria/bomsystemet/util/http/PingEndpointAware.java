package no.soprasteria.bomsystemet.util.http;

import java.net.URI;

public interface PingEndpointAware extends Pingable {

    URI pingEndpoint();

    String name();
}
