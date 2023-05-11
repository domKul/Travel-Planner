package com.planner.travelplanner.service.restTemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateSearchFlights {

    private final RestTemplate restTemplate;

    public RestTemplateSearchFlights(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    private URI urlBuilder(String name, String locale) {
        return UriComponentsBuilder
                .fromHttpUrl("https://skyscanner50.p.rapidapi.com/api/v1/searchFlights")
                .queryParam("origin", name)
                .queryParam("destination",locale)
                .queryParam("date",locale)
                .build()
                .encode()
                .toUri();
    }
    public ResponseEntity<String> searchLocations(String name, String locale) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", "f243aef89dmshe81c48fa6dfb27ep142049jsne19c66e2bb54");
        headers.set("X-RapidAPI-Host", "booking-com.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String url = urlBuilder(name,locale).toString();
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }
}
