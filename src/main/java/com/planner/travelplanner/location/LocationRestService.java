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
import java.util.List;

@Service
class LocationRestService {

    private final RestTemplate restTemplate;
    private final LocationService locationService;


    LocationRestService(RestTemplateBuilder builder, LocationService locationService) {
        this.restTemplate = builder.build();
        this.locationService = locationService;
    }

    URI urlBuilder(String name, String locale) {
        return UriComponentsBuilder
                .fromHttpUrl("https://booking-com.p.rapidapi.com/v1/hotels/locations")
                .queryParam("name", name)
                .queryParam("locale", locale)
                .build()
                .encode()
                .toUri();
    }

    @Transactional
    public ResponseEntity<Void> searchLocations(String name, String locale) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", "f243aef89dmshe81c48fa6dfb27ep142049jsne19c66e2bb54");
        headers.set("X-RapidAPI-Host", "booking-com.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String url = urlBuilder(name, locale).toString();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<LocationDTO> locationDTOS = null;
        try {
            locationDTOS = objectMapper.readValue(responseEntity.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (locationDTOS != null) {
            locationService.saveLocations(locationDTOS);
        }
        return null;
    }


}
