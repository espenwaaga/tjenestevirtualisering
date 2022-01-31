package no.soprasteria.felles.http;

import static no.soprasteria.felles.http.JacksonObjectMapper.mapper;
import static no.soprasteria.felles.http.RestClientSupportProdusent.connectionManager;
import static no.soprasteria.felles.http.RestClientSupportProdusent.createKeepAliveStrategy;
import static no.soprasteria.felles.http.RestClientSupportProdusent.defaultRequestConfig;

import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.apache.connector.ApacheHttpClientBuilderConfigurator;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.RequestEntityProcessing;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class AbstractJerseyRestKlient {

    protected final Client client;

    protected AbstractJerseyRestKlient() {
        this(mapper);
    }

    private AbstractJerseyRestKlient(ObjectMapper mapper) {
        var cfg = new ClientConfig();
        cfg.register(jacksonProvider(mapper));
        cfg.connectorProvider(new ApacheConnectorProvider());
        cfg.register((ApacheHttpClientBuilderConfigurator) b ->
                b.setKeepAliveStrategy(createKeepAliveStrategy(30))
                .setDefaultRequestConfig(defaultRequestConfig())
                .setRetryHandler(new HttpRequestRetryHandler())
                .setConnectionManager(connectionManager()));
        cfg.property(ClientProperties.FOLLOW_REDIRECTS, Boolean.FALSE);
        client = ClientBuilder.newClient(cfg).property(ClientProperties.REQUEST_ENTITY_PROCESSING, RequestEntityProcessing.BUFFERED);
    }

    private static JacksonJaxbJsonProvider jacksonProvider(ObjectMapper mapper) {
        return Optional.ofNullable(mapper)
                .map(m -> new JacksonJaxbJsonProvider(m, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS))
                .orElse(new JacksonJaxbJsonProvider());
    }
}
