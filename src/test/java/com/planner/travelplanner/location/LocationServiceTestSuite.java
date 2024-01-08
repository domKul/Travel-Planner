package com.planner.travelplanner.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LocationServiceTestSuite {

    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private LocationRepository locationRepository;

    private Location location1;
    private Location location2;

    void dataForTest() {
        location1 = new Location();
        location2 = new Location();
    }
}
