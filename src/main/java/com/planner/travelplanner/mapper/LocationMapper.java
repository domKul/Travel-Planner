package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Location;
import com.planner.travelplanner.domain.dto.LocationDTO;
import com.planner.travelplanner.repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationMapper {

    private final LocationRepository locationRepository;

    public LocationMapper(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    @Transactional
    public ResponseEntity<List<LocationDTO>> saveLocations(List<LocationDTO> locationDTOs) {
        List<Location> locations = locationDTOs.stream()
                .map(this::mapToLocation)
                .collect(Collectors.toList());
        locationRepository.saveAll(locations);
        return null;
    }

    public Location mapToLocation(LocationDTO locationDTO){
        return new Location(IdType.EMPTY_ID.getId(),
                locationDTO.getLabel(),
                locationDTO.getDest_id(),
                locationDTO.getRegion(),
                locationDTO.getName(),
                locationDTO.getCountry(),
                locationDTO.getHotels(),
                locationDTO.getTimezone());
    }

}
