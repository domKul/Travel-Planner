package com.planner.travelplanner.location;

import com.planner.travelplanner.enums.IdType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class LocationMapper {

    Location mapToLocation(LocationDTO locationDTO) {
        return new Location(IdType.EMPTY_ID.getId(),
                locationDTO.getLabel(),
                locationDTO.getDest_id(),
                locationDTO.getDestination_type(),
                locationDTO.getRegion(),
                locationDTO.getName(),
                locationDTO.getCountry(),
                locationDTO.getHotels(),
                locationDTO.getTimezone());
    }

    LocationDTO mapToLocationDTO(Location location) {
        return new LocationDTO(
                location.getLabel(),
                location.getRegion(),
                location.getDest_id(),
                location.getDestination_type(),
                location.getName(),
                location.getCountry(),
                location.getHotels(),
                location.getTimezone());
    }

    List<LocationDTO> mapToLocationListDTO(List<Location> location) {
        return location.stream()
                .map(this::mapToLocationDTO)
                .toList();
    }

    List<Location> mapToLocationListFromDTO(List<LocationDTO> locationDTOs) {
        return locationDTOs.stream()
                .map(this::mapToLocation)
                .toList();
    }
}

