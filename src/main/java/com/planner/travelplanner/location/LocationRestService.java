package com.planner.travelplanner.location;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
class LocationRestService {

    private final RestTemplate restTemplate;
    private final LocationService locationService;
    private final LocationApiConfig locationApiConfig;
    private final String HTTP_URL="https://booking-com.p.rapidapi.com/v1/hotels/locations";

    LocationRestService(RestTemplateBuilder builder, LocationService locationService, LocationApiConfig locationApiConfig) {
        this.restTemplate = builder.build();
        this.locationService = locationService;
        this.locationApiConfig = locationApiConfig;
    }

    URI urlBuilder(String name, String locale) {
        return UriComponentsBuilder
                .fromHttpUrl(HTTP_URL)
                .queryParam("name", name)
                .queryParam("locale", locale)
                .build()
                .encode()
                .toUri();
    }

    void searchLocations(String name, String locale) {
        HttpHeaders headers = createHttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String url = urlBuilder(name, locale).toString();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<LocationDTO> locationDTOS = new ArrayList<>();
        try {
            locationDTOS = objectMapper.readValue(responseEntity.getBody(), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.getMessage();
        }
        if (locationDTOS != null) {
            locationService.saveLocations(locationDTOS);
        }
        ResponseEntity.notFound().build();
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", locationApiConfig.getLocationApiKey());
        headers.set("X-RapidAPI-Host", locationApiConfig.getLocationApiHost());
        return headers;
    }
}
