package com.planner.travelplanner.location;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class LocationApiConfig {

    @Value(value = "${booking.api.key}")
    private String locationApiKey;
    @Value(value = "${booking.api.host}")
    private String locationApiHost;

    public String getLocationApiKey() {
        return locationApiKey;
    }

    public String getLocationApiHost() {
        return locationApiHost;
    }
}
