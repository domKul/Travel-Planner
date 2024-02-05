package travelplanner.currency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class CurrencyHttpClientConfigTest {


    @Autowired
    private CurrencyHttpClientConfig currencyHttpClientConfig;

    @Test
    void getUriShouldConstructCorrectUri() {
        //Given
        String from ="USD";
        String to = "EUR";
        BigDecimal amount = new BigDecimal("750");
        //When
        URI uri = currencyHttpClientConfig.getUri(from, to, amount);
        //Then
        String expectedUrl = "https://currency-conversion-and-exchange-rates.p.rapidapi.com/convert?from=USD&to=EUR&amount=750";
        assertEquals(expectedUrl, uri.toString());
    }
}
