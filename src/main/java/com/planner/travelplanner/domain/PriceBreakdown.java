package com.planner.travelplanner.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.planner.travelplanner.domain.dto.hotel.GrossPriceDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "priceBreakdown")
public class PriceBreakdown {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long priceBreakdown;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonProperty("grossPrice")
    private GrossPrice grossPrice;

}
