package travelplanner.location;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
class LocationClientConnectionData {

    @Value("${rapidapi.api.key}")
    private String apiLocationKey;
    @Value("${location.api.host}")
    private String apiLocationHost;
    @Value("${location.api.url}")
    private String apiLocationUrl;

}
