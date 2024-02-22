package travelplanner.destination;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
 class DestinationHttpClientConfig {
    private final DestinationClientConnectionData destinationClientConnectionData;

    URI urlBuilder(String orderedBy, int adults_number, String checkin_date,
                   String filter_by_currency, int dest_id,
                   String locale, String checkout_date, String units,
                   int room_number, String dest_type) {
        return UriComponentsBuilder
                .fromHttpUrl(destinationClientConnectionData.getApiDestinationUrl())
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

     HttpHeaders header() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", destinationClientConnectionData.getApiDestinationKey());
        headers.set("X-RapidAPI-Host", destinationClientConnectionData.getApiDestinationHost());
        return headers;
    }
}
