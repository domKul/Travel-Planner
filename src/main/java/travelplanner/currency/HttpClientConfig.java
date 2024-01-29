package travelplanner.currency;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@Component
class HttpClientConfig {

    HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", "f243aef89dmshe81c48fa6dfb27ep142049jsne19c66e2bb54");
        headers.set("X-RapidAPI-Host", "currency-conversion-and-exchange-rates.p.rapidapi.com");
        return headers;
    }

    URI getUri(String from, String to, BigDecimal amount) {
        return UriComponentsBuilder
                .fromUriString("https://currency-conversion-and-exchange-rates.p.rapidapi.com/convert")
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("amount", amount)
                .build()
                .toUri();
    }
}
