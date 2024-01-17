package com.planner.travelplanner.location;

import com.planner.travelplanner.enums.ExceptionMessages;
import com.planner.travelplanner.exception.NotFoundException;
import com.planner.travelplanner.jpa.AbstractRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
class LocationService extends AbstractRepository<LocationRepository, Location> {

    private final LocationMapper locationMapper;
    private final LocationRepository locationRepository;

    LocationService(LocationMapper locationMapper, LocationRepository locationRepository) {
        this.locationMapper = locationMapper;
        this.locationRepository = locationRepository;
    }

     void saveLocations(List<LocationDTO> locationDTOs) {
        if (locationDTOs.isEmpty()){
            throw new NotFoundException(ExceptionMessages.BODY_IS_NULL);
        }
        try{
            List<Location> locations = locationMapper.mapToLocationListFromDTO(locationDTOs);
            LOGGER.info("Localizations saved");
            locationRepository.saveAll(locations);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Error saving locations: " + e.getMessage());
        }
    }

     void deleteLocation(long locationId) {
        Location location = getLocation(locationId);
        LOGGER.info("Localization deleted");
        locationRepository.delete(location);
    }

    List<LocationDTO> getLocationsFromDB() {
        List<Location> all = locationRepository.findAll();
        if (!all.isEmpty()){
            LOGGER.info("List of localization loaded");
            return locationMapper.mapToLocationListDTO(all);
        }
        return new ArrayList<>();
    }

    LocationDTO getLocationById(long locationId) {
        Location entity = findEntity(locationRepository, locationId);
        return locationMapper.mapToLocationDTO(entity);

    }

    private Location getLocation(long locationId) {
        return findEntity(locationRepository,locationId);
    }
}
