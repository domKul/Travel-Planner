package travelplanner.currency;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitWebConfig
@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CurrencyClient currencyClient;
    @InjectMocks
    private CurrencyController currencyController;

    @Test
    void mockCheck() {
        assertNotNull(mockMvc);
    }

    @Test
    void shouldConvertCurrency() throws Exception {
        //Given
        String from = "USD";
        String to = "EUR";
        BigDecimal amount = new BigDecimal("100");

        Currency mockedCurrency = new Currency(new Query(from, to, amount), new BigDecimal("0.91"));
        Mockito.when(currencyClient.convert(from, to, amount)).thenReturn(mockedCurrency);

        //When
        mockMvc.perform(MockMvcRequestBuilders.get("/currency/conversion")
                .param("from",from)
                .param("to",to)
                .param("amount",amount.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(mockedCurrency)));

    }
}
