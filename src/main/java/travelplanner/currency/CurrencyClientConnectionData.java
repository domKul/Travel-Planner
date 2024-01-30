package travelplanner.currency;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class CurrencyClientConnectionData {

    @Value("${rapidapi.api.key}")
    private String apiCurrencyKey;
    @Value("${currency.api.host}")
    private String apiCurrencyHost;
    @Value("${currency.api.url}")
    private String apiCurrencyUrl;

    public String getApiCurrencyKey() {
        return apiCurrencyKey;
    }

    public String getApiCurrencyHost() {
        return apiCurrencyHost;
    }

    public String getApiCurrencyUrl() {
        return apiCurrencyUrl;
    }
}
