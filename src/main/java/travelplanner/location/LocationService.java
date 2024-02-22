package travelplanner.location;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import travelplanner.exception.ExceptionMessages;
import travelplanner.exception.NotFoundException;
import travelplanner.jpa.AbstractRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
class LocationService extends AbstractRepository<LocationRepository, Location> {

    private final LocationMapper locationMapper;
    private final LocationRepository locationRepository;

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
