package com.planner.travelplanner.config.hotelApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DestinationApiConfig {

    @Value("f243aef89dmshe81c48fa6dfb27ep142049jsne19c66e2bb54")
    private String apiKey;
    @Value("booking-com.p.rapidapi.com")
    private String apiHost;

    public String getApiKey() {
        return apiKey;
    }

    public String getApiHost() {
        return apiHost;
    }
}
