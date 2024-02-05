package travelplanner.currency;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Collections;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CurrencyClientTest {

    @Mock
    private CurrencyHttpClientConfig currencyHttpClientConfig;

    @InjectMocks
    private CurrencyClient currencyClient = new CurrencyClient(currencyHttpClientConfig);

    @Mock
    private RestTemplate restTemplate;
    @BeforeEach
    public void setUp() {
        currencyHttpClientConfig = Mockito.mock(CurrencyHttpClientConfig.class);
        restTemplate = Mockito.mock(RestTemplate.class);
        currencyClient = new CurrencyClient(currencyHttpClientConfig);
        ReflectionTestUtils.setField(currencyClient, "restTemplate", restTemplate);
    }

    @Test
    void convertShouldCallExternalApiAndReturnCurrency() {
        //Given
        String from = "USD";
        String to = "EUR";
        BigDecimal amount = new BigDecimal("100");
        URI expectedUri = URI.create("http://example.com/api/currency");
        HttpHeaders expectedHeaders = new HttpHeaders();
        expectedHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String expectedJson = "{\"rate\": 0.91}";
        when(currencyHttpClientConfig.getUri(from, to, amount)).thenReturn(expectedUri);
        when(currencyHttpClientConfig.getHttpHeaders()).thenReturn(expectedHeaders);
        when(restTemplate.exchange(expectedUri, HttpMethod.GET, new HttpEntity<>(expectedHeaders), String.class))
                .thenReturn(new ResponseEntity<>(expectedJson, HttpStatus.OK));
        //When
        Currency actualCurrency = currencyClient.convert(from, to, amount);
        //Then
        assertEquals(Currency.class, actualCurrency.getClass());
        Mockito.verify(currencyHttpClientConfig).getUri(from, to, amount);
        Mockito.verify(currencyHttpClientConfig).getHttpHeaders();
        Mockito.verify(restTemplate).exchange(expectedUri, HttpMethod.GET, new HttpEntity<>(expectedHeaders), String.class);
    }
}
