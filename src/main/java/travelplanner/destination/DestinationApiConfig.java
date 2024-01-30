package travelplanner.destination;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class DestinationApiConfig {

    @Value("${rapidapi.api.key}")
    private String apiKey;
    @Value("${destination.api.host}")
    private String apiHost;

    String getApiKey() {
        return apiKey;
    }

    String getApiHost() {
        return apiHost;
    }
}
