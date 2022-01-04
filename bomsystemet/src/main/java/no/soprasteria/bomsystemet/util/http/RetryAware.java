package no.soprasteria.bomsystemet.util.http;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Retryable(include = {
        RestClientException.class,
        WebClientException.class }, exclude = {
                HttpClientErrorException.NotFound.class,
                HttpClientErrorException.Forbidden.class,
                HttpClientErrorException.BadRequest.class,
                HttpClientErrorException.Unauthorized.class,
                WebClientResponseException.NotFound.class,
                WebClientResponseException.Forbidden.class,
                WebClientResponseException.BadRequest.class,
                WebClientResponseException.Unauthorized.class }, maxAttemptsExpression = "#{${rest.retry.attempts:3}}", backoff = @Backoff(delayExpression = "#{${rest.retry.delay:1000}}"))

public interface RetryAware {

}
