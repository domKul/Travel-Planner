package travelplanner.currency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import travelplanner.enums.ExceptionMessages;
import travelplanner.exception.ClientException;

import java.math.BigDecimal;
import java.net.URI;

@Service
class CurrencyClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpClientConfig httpClientConfig;

    CurrencyClient(HttpClientConfig httpClientConfig) {
        this.httpClientConfig = httpClientConfig;
    }

    Currency convert(String from, String to, BigDecimal amount) {
        URI url = httpClientConfig.getUri(from, to, amount);
        HttpHeaders headers = httpClientConfig.getHttpHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper mapper = new ObjectMapper();
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("Error converting currency");
            }
            return mapper.readValue(response.getBody(), Currency.class);
        } catch (HttpClientErrorException | JsonProcessingException e) {
            throw new ClientException(ExceptionMessages.CONNECTION_PROBLEM);
        }
    }
}
