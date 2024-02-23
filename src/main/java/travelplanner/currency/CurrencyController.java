package travelplanner.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
class CurrencyController {

    private final CurrencyClient currencyClient;

    @GetMapping(value = "/conversion")
    ResponseEntity<Currency> convertCurrency(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(currencyClient.convert(from,to,amount));
    }
}
