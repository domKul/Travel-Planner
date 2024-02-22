package travelplanner.currency;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
class CurrencyClientConnectionData {

    @Value("${rapidapi.api.key}")
    private String apiCurrencyKey;
    @Value("${currency.api.host}")
    private String apiCurrencyHost;
    @Value("${currency.api.url}")
    private String apiCurrencyUrl;
}
