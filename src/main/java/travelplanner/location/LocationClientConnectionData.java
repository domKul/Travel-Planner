package travelplanner.location;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class LocationClientConnectionData {

    @Value("${rapidapi.api.key}")
    private String apiLocationKey;
    @Value("${location.api.host}")
    private String apiLocationHost;
    @Value("${location.api.url}")
    private String apiLocationUrl;

    public String getApiLocationKey() {
        return apiLocationKey;
    }

    public String getApiLocationHost() {
        return apiLocationHost;
    }

    public String getApiLocationUrl() {
        return apiLocationUrl;
    }
}
