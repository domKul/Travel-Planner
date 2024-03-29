package travelplanner.currency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import travelplanner.exception.ClientException;
import travelplanner.exception.ExceptionMessages;

import java.math.BigDecimal;
import java.net.URI;

@Service
@AllArgsConstructor
class CurrencyClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final CurrencyHttpClientConfig currencyHttpClientConfig;

    Currency convert(String from, String to, BigDecimal amount) {
        URI url = currencyHttpClientConfig.getUri(from, to, amount);
        HttpHeaders headers = currencyHttpClientConfig.getHttpHeaders();
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
