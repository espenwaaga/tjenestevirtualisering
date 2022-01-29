package no.soprasteria.vtp.config;

import static no.soprasteria.felles.http.JacksonObjectMapper.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonKonfigurasjon {

    @Bean
    @Primary
    public ObjectMapper customObjectmapper() {
        return mapper;
    }
}
