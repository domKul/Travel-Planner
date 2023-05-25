package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.Location;
import com.planner.travelplanner.mapper.LocationMapper;
import com.planner.travelplanner.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LocationServiceTestSuite {
    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private LocationRepository locationRepository;

    private Location location1;
    private Location location2;

    public void dataForTest(){
        location1 = new Location();
        location2 = new Location();

    }
}
