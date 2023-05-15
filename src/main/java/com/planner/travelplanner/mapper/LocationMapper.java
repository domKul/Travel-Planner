package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Location;
import com.planner.travelplanner.domain.dto.LocationDTO;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

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
