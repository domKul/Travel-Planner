package travelplanner.destination;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class DestinationClientConnectionData {
    @Value("${rapidapi.api.key}")
    private String apiDestinationKey;
    @Value("${destination.api.host}")
    private String apiDestinationHost;
    @Value("${destination.api.url}")
    private String apiDestinationUrl;

    public String getApiDestinationKey() {
        return apiDestinationKey;
    }

    public String getApiDestinationHost() {
        return apiDestinationHost;
    }

    public String getApiDestinationUrl() {
        return apiDestinationUrl;
    }
}
