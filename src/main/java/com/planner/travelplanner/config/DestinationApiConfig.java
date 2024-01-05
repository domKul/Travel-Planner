package com.planner.travelplanner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DestinationApiConfig {

    @Value("${booking.api.key}")
    private String apiKey;
    @Value("${booking.api.host}")
    private String apiHost;

    public String getApiKey() {
        return apiKey;
    }

    public String getApiHost() {
        return apiHost;
    }
}
