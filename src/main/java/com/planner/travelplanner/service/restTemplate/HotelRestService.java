package com.planner.travelplanner.service.restTemplate;

import com.planner.travelplanner.domain.dto.hotel.GrossPriceDTO;
import com.planner.travelplanner.domain.dto.hotel.HotelDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class HotelRestService {

    private final RestTemplate restTemplate;

    public HotelRestService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    private URI urlBuilder(String orderedby, int adults_number, String checkin_date,
                           String filter_by_currency, int dest_id,
                           String locale, String checkout_date, String units,
                           int room_number, String dest_type) {
        return UriComponentsBuilder
                .fromHttpUrl("https://booking-com.p.rapidapi.com/v2/hotels/search")
                .queryParam("order_by", orderedby)
                .queryParam("adults_number",adults_number)
                .queryParam("checkin_date",checkin_date)
                .queryParam("filter_by_currency",filter_by_currency)
                .queryParam("dest_id",dest_id)
                .queryParam("locale",locale)
                .queryParam("checkout_date",checkout_date)
                .queryParam("units",units)
                .queryParam("room_number",room_number)
                .queryParam("dest_type",dest_type)
                .build()
                .encode()
                .toUri();
    }
    public ResponseEntity<HotelDTO> searchHotel(String orderedby, int adults_number, String checkin_date,
                                                  String filter_by_currency, int dest_id,
                                                  String locale, String checkout_date, String units,
                                                  int room_number, String dest_type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", "f243aef89dmshe81c48fa6dfb27ep142049jsne19c66e2bb54");
        headers.set("X-RapidAPI-Host", "booking-com.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String url = urlBuilder( orderedby,  adults_number,  checkin_date,
                 filter_by_currency,  dest_id,
         locale,  checkout_date,  units,  room_number,  dest_type).toString();
        return restTemplate.exchange(url, HttpMethod.GET, entity, HotelDTO.class);
    }
}
