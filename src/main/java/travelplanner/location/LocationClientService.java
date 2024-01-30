package travelplanner.location;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
class LocationClientService {

    private final RestTemplate restTemplate;
    private final LocationService locationService;
    private final LocationHttpClientConfig locationHttpClientConfig;


    LocationClientService(RestTemplateBuilder builder,
                          LocationService locationService,
                          LocationHttpClientConfig locationHttpClientConfig) {
        this.restTemplate = builder.build();
        this.locationService = locationService;
        this.locationHttpClientConfig = locationHttpClientConfig;
    }

    ResponseEntity<Void> searchLocations(String name, String locale) {
        HttpHeaders headers = locationHttpClientConfig.locationHeader();

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String url = locationHttpClientConfig.locationUrlBuilder(name, locale).toString();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<LocationDTO> locationDTOS = null;
        try {
            locationDTOS = objectMapper.readValue(responseEntity.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (locationDTOS != null) {
            locationService.saveLocations(locationDTOS);
        }
        return ResponseEntity.notFound().build();
    }
}
