package travelplanner.currency;

import java.math.BigDecimal;

record Query(
        String from,
        String to,
        BigDecimal amount
) {
}
