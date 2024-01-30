package travelplanner.location;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
 class LocationHttpClientConfig {
    private final LocationClientConnectionData locationClientConnectionData;

    LocationHttpClientConfig(LocationClientConnectionData locationClientConnectionData) {
        this.locationClientConnectionData = locationClientConnectionData;
    }

    HttpHeaders locationHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", locationClientConnectionData.getApiLocationKey());
        headers.set("X-RapidAPI-Host", locationClientConnectionData.getApiLocationHost());
        return headers;
    }
    URI locationUrlBuilder(String name, String locale) {
        return UriComponentsBuilder
                .fromHttpUrl(locationClientConnectionData.getApiLocationUrl())
                .queryParam("name", name)
                .queryParam("locale", locale)
                .build()
                .encode()
                .toUri();
    }
}
