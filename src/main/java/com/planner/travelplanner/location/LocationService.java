package com.planner.travelplanner.location;

import com.planner.travelplanner.exception.LocationNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

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
        Location location = getLocation(locationId);
        locationRepository.delete(location);
    }

    List<LocationDTO> getLocationsFromDB() {
        return locationMapper.mapToLocationListDTO(locationRepository.findAll());
    }

    LocationDTO getLocationById(long locationId) {
        boolean isExist = locationRepository.existsById(locationId);
        if (!isExist){
            throw new LocationNotFoundException();
        }
        return locationMapper.mapToLocationDTO(getLocation(locationId));

    }

    private Location getLocation(long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(LocationNotFoundException::new);
    }
}
