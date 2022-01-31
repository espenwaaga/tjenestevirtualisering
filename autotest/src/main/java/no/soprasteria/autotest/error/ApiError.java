package no.soprasteria.autotest.error;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiError(HttpStatus status,
                       String message) {


    @JsonCreator
    public ApiError(int status, String message) {
        this(HttpStatus.valueOf(status), message);
    }

}
