package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.Location;
import com.planner.travelplanner.domain.dto.location.LocationDTO;
import com.planner.travelplanner.domain.exception.LocationNotFoundException;
import com.planner.travelplanner.mapper.LocationMapper;
import com.planner.travelplanner.repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationMapper locationMapper;
    private final LocationRepository locationRepository;

    public LocationService(LocationMapper locationMapper, LocationRepository locationRepository) {
        this.locationMapper = locationMapper;
        this.locationRepository = locationRepository;
    }
    @Transactional
    public void saveLocations(List<LocationDTO> locationDTOs) {
        List<Location> locations = locationMapper.mapToLocationListFromDTO(locationDTOs);
        locationRepository.saveAll(locations);
    }
    @Transactional
    public void deleteLocation(long locationId) {
        Optional<Location> findById = locationRepository.findById(locationId);
        if (findById.isPresent()) {
            locationRepository.deleteById(locationId);
        } else {
            throw new LocationNotFoundException();
        }
    }
    public List<LocationDTO> getLocationsFromDB(){
        return locationMapper.mapToLocationListDTO(locationRepository.findAll());
    }

    public LocationDTO getLocationById(long locationId) {
        if (locationRepository.existsById(locationId)) {
            return locationMapper.mapToLocationDTO(locationRepository.findById(locationId).get());
        } else {
            throw new LocationNotFoundException();
        }
    }
}
