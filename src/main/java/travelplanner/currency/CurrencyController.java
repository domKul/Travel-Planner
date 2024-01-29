package travelplanner.currency;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency")
class CurrencyController {
    private final CurrencyClient currencyClient;

    CurrencyController(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    @GetMapping(value = "/conversion")
    ResponseEntity<Currency> convertCurrency(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(currencyClient.convert(from,to,amount));
    }
}
