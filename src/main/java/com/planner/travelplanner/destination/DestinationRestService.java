package com.planner.travelplanner.destination;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
class DestinationRestService {

    private final RestTemplate restTemplate;
    private final DestinationRepository destinationRepository;
    private final DestinationApiConfig destinationApiConfig;
    private final String URL ="https://booking-com.p.rapidapi.com/v2/hotels/search";

    DestinationRestService(RestTemplateBuilder builder, DestinationRepository destinationRepository,
                           DestinationApiConfig destinationApiConfig) {
        this.restTemplate = builder.build();
        this.destinationRepository = destinationRepository;
        this.destinationApiConfig = destinationApiConfig;
    }

    URI urlBuilder(String orderedBy, int adults_number, String checkin_date,
                   String filter_by_currency, int dest_id,
                   String locale, String checkout_date, String units,
                   int room_number, String dest_type) {
        return UriComponentsBuilder
                .fromHttpUrl(URL)
                .queryParam("order_by", orderedBy)
                .queryParam("adults_number", adults_number)
                .queryParam("checkin_date", checkin_date)
                .queryParam("filter_by_currency", filter_by_currency)
                .queryParam("dest_id", dest_id)
                .queryParam("locale", locale)
                .queryParam("checkout_date", checkout_date)
                .queryParam("units", units)
                .queryParam("room_number", room_number)
                .queryParam("dest_type", dest_type)
                .build()
                .encode()
                .toUri();
    }

    private HttpHeaders header() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", destinationApiConfig.getDestinationApiKey());
        headers.set("X-RapidAPI-Host", destinationApiConfig.getDestinationApiHost());
        return headers;
    }

    DestinationDTO searchHotelWithSaveToData(String orderedBy,
                                             int adults_number,
                                             String checkin_date,
                                             String filter_by_currency,
                                             int dest_id,
                                             String locale,
                                             String checkout_date,
                                             String units,
                                             int room_number,
                                             String dest_type) {

        HttpEntity<String> entity = new HttpEntity<>("parameters", header());
        String url = urlBuilder(orderedBy, adults_number, checkin_date,
                filter_by_currency, dest_id,
                locale, checkout_date, units, room_number, dest_type).toString();
        ResponseEntity<DestinationDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, DestinationDTO.class);
        List<Destination> destinations = new ArrayList<>();

        for (Result hotelDTO : Objects.requireNonNull(responseEntity.getBody()).results()) {
            if (destinationRepository.existsByName(hotelDTO.getName())) {
                continue;
            }

            Destination destination = createDestination(hotelDTO);
            destinations.add(destination);
        }
        destinationRepository.saveAll(destinations);
        return responseEntity.getBody();
    }

    private static Destination createDestination(Result hotelDTO) {
        Destination destination = new Destination();
        destination.setIdName(hotelDTO.getId());
        destination.setCountryCode(hotelDTO.getCountryCode());
        destination.setName(hotelDTO.getName());
        if (hotelDTO.getPriceBreakdown() != null) {
            destination.setCurrency(hotelDTO.getPriceBreakdown().getGrossPrice().currency());
            destination.setDestinationPrice(hotelDTO.getPriceBreakdown().getGrossPrice().value());
        }
        return destination;
    }

    ResponseEntity<DestinationDTO> searchHotel(String orderedBy, int adults_number, String checkin_date,
                                               String filter_by_currency, int dest_id,
                                               String locale, String checkout_date, String units,
                                               int room_number, String dest_type) {
        HttpEntity<String> entity = new HttpEntity<>("parameters", header());
        String url = urlBuilder(orderedBy, adults_number, checkin_date,
                filter_by_currency, dest_id,
                locale, checkout_date, units, room_number, dest_type).toString();
        return restTemplate.exchange(url, HttpMethod.GET, entity, DestinationDTO.class);
    }

}
