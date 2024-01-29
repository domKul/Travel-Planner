package travelplanner.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
record Currency(
                Query query,
                BigDecimal result) {
}
