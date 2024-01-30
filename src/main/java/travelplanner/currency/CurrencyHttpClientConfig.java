package travelplanner.currency;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@Component
class CurrencyHttpClientConfig {
    private final CurrencyClientConnectionData currencyClientConnectionData;

    CurrencyHttpClientConfig(CurrencyClientConnectionData currencyClientConnectionData) {
        this.currencyClientConnectionData = currencyClientConnectionData;
    }

    HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", currencyClientConnectionData.getApiCurrencyKey());
        headers.set("X-RapidAPI-Host", currencyClientConnectionData.getApiCurrencyHost());
        return headers;
    }

    URI getUri(String from, String to, BigDecimal amount) {
        return UriComponentsBuilder
                .fromUriString(currencyClientConnectionData.getApiCurrencyUrl())
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("amount", amount)
                .build()
                .toUri();
    }
}
