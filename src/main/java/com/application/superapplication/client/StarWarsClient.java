package com.application.superapplication.client;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class StarWarsClient {


    public Object getPeopleById(Long peopleId) {
        Map<String, ?> parameters = Map.of("peopleId", peopleId);
        try {
            return new RestTemplateBuilder().build().getForObject(
                    "%s/people/{peopleId}".formatted("https://swapi.dev/api"),
                    Object.class,
                    parameters
            );
        } catch (HttpClientErrorException ex) {
            throw new RuntimeException("Error when calling Star Wars API", ex);
        }
    }
}
