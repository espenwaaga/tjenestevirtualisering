package no.soprasteria.vtp.config;

import static no.soprasteria.felles.http.JacksonObjectMapper.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonConfiguration {

    @Bean
    @Primary
    public ObjectMapper customObjectmapper() {
        return mapper;
    }
}
