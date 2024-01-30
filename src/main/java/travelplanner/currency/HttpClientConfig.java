package travelplanner.currency;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@Component
class HttpClientConfig {
    private final ConnectionData connectionData;

    HttpClientConfig(ConnectionData connectionData) {
        this.connectionData = connectionData;
    }

    HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", connectionData.getApiCurrencyKey());
        headers.set("X-RapidAPI-Host", connectionData.getApiCurrencyHost());
        return headers;
    }

    URI getUri(String from, String to, BigDecimal amount) {
        return UriComponentsBuilder
                .fromUriString(connectionData.getApiCurrencyUrl())
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("amount", amount)
                .build()
                .toUri();
    }
}
