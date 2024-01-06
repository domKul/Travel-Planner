package com.planner.travelplanner.location;

import com.planner.travelplanner.exception.LocationNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class LocationService {

    private final LocationMapper locationMapper;
    private final LocationRepository locationRepository;

    LocationService(LocationMapper locationMapper, LocationRepository locationRepository) {
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

    List<LocationDTO> getLocationsFromDB() {
        return locationMapper.mapToLocationListDTO(locationRepository.findAll());
    }

    LocationDTO getLocationById(long locationId) {
        if (locationRepository.existsById(locationId)) {
            return locationMapper.mapToLocationDTO(locationRepository.findById(locationId).get());
        } else {
            throw new LocationNotFoundException();
        }
    }
}
