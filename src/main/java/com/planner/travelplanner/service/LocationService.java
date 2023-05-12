package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.Location;
import com.planner.travelplanner.domain.dto.LocationDTO;
import com.planner.travelplanner.mapper.LocationMapper;
import com.planner.travelplanner.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationMapper locationMapper;
    private final LocationRepository locationRepository;

    public LocationService(LocationMapper locationMapper, LocationRepository locationRepository) {
        this.locationMapper = locationMapper;
        this.locationRepository = locationRepository;
    }

    public void saveLocations(List<LocationDTO> locationDTOs) {
        List<Location> locations = locationDTOs.stream()
                .map(locationMapper::mapToLocation)
                .collect(Collectors.toList());
        locationRepository.saveAll(locations);
    }
}
