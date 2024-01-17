package com.planner.travelplanner.destination;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class DestinationApiConfig {

    @Value("${booking.api.key}")
    private String destinationApiKey;
    @Value("${booking.api.host}")
    private String destinationApiHost;

    public String getDestinationApiKey() {
        return destinationApiKey;
    }

    public String getDestinationApiHost() {
        return destinationApiHost;
    }
}
