package com.planner.travelplanner.service.restTemplate;

import com.planner.travelplanner.config.hotelApi.HotelApiConfig;
import com.planner.travelplanner.domain.Hotel;
import com.planner.travelplanner.domain.dto.hotel.HotelDTO;
import com.planner.travelplanner.domain.dto.hotel.Result;
import com.planner.travelplanner.repository.HotelRepository;
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
public class HotelRestService {

    private final RestTemplate restTemplate;
    private final HotelRepository hotelRepository;

    private final HotelApiConfig hotelApiConfig;

    public HotelRestService(RestTemplateBuilder builder, HotelRepository hotelRepository, HotelApiConfig hotelApiConfig) {
        this.restTemplate = builder.build();
        this.hotelRepository = hotelRepository;
        this.hotelApiConfig = hotelApiConfig;
    }

    public URI urlBuilder(String orderedby, int adults_number, String checkin_date,
                          String filter_by_currency, int dest_id,
                          String locale, String checkout_date, String units,
                          int room_number, String dest_type) {
        return UriComponentsBuilder
                .fromHttpUrl("https://booking-com.p.rapidapi.com/v2/hotels/search")
                .queryParam("order_by", orderedby)
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

    private  HttpHeaders header(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", hotelApiConfig.getApiKey());
        headers.set("X-RapidAPI-Host", hotelApiConfig.getApiHost());
        return headers;
    }

    public ResponseEntity<HotelDTO> searchHotelWithSaveToData(String orderedby, int adults_number, String checkin_date,
                                                 String filter_by_currency, int dest_id,
                                                 String locale, String checkout_date, String units,
                                                 int room_number, String dest_type) {

        HttpEntity<String> entity = new HttpEntity<>("parameters", header());
        String url = urlBuilder( orderedby,  adults_number,  checkin_date,
                filter_by_currency,  dest_id,
                locale,  checkout_date,  units,  room_number,  dest_type).toString();
        ResponseEntity<HotelDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, HotelDTO.class);
        List<Hotel> hotels = new ArrayList<>();

        for (Result hotelDTO : Objects.requireNonNull(responseEntity.getBody()).getResults()) {
            Hotel hotel = new Hotel();
            hotel.setIdName(hotelDTO.getId());

            hotel.setCountryCode(hotelDTO.getCountryCode());
            hotel.setCurrency(hotelDTO.getCurrency());
            hotel.setName(hotelDTO.getName());
            hotels.add(hotel);
        }
        hotelRepository.saveAll(hotels);
        return responseEntity;
    }

    public ResponseEntity<HotelDTO> searchHotel(String orderedby, int adults_number, String checkin_date,
                                String filter_by_currency, int dest_id,
                                String locale, String checkout_date, String units,
                                int room_number, String dest_type) {

        HttpEntity<String> entity = new HttpEntity<>("parameters", header());
        String url = urlBuilder( orderedby,  adults_number,  checkin_date,
                filter_by_currency,  dest_id,
                locale,  checkout_date,  units,  room_number,  dest_type).toString();
       return restTemplate.exchange(url, HttpMethod.GET, entity, HotelDTO.class);

    }

}
