package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Location;
import com.planner.travelplanner.domain.dto.location.LocationDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationMapper {

    public Location mapToLocation(LocationDTO locationDTO) {
        return new Location(IdType.EMPTY_ID.getId(),
                locationDTO.getLabel(),
                locationDTO.getDest_id(),
                locationDTO.getRegion(),
                locationDTO.getName(),
                locationDTO.getCountry(),
                locationDTO.getHotels(),
                locationDTO.getTimezone());
    }

    public LocationDTO mapToLocationDTO(Location location) {
        return new LocationDTO(
                location.getLabel(),
                location.getRegion(),
                location.getDest_id(),
                location.getName(),
                location.getCountry(),
                location.getHotels(),
                location.getTimezone());
    }

    public List<LocationDTO> mapToLocationListDTO(List<Location> location) {
        return location.stream()
                .map(this::mapToLocationDTO)
                .toList();
    }

    public List<Location> mapToLocationListFromDTO(List<LocationDTO> locationDTOs) {
        return locationDTOs.stream()
                .map(this::mapToLocation)
                .toList();
    }

}

