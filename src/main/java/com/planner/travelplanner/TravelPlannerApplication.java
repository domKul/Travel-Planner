package com.planner.travelplanner;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@OpenAPIDefinition
public class TravelPlannerApplication {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {

        SpringApplication.run(TravelPlannerApplication.class, args);

    }

}
