package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Destination;
import com.planner.travelplanner.domain.dto.destination.DestinationDTOForGet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DestinationMapper {
    public DestinationDTOForGet mapToDestinationResultDTO(Destination destination) {
        return new DestinationDTOForGet(destination.getDestinationId(),
                destination.getIdName(),
                destination.getName(),
                destination.getCountryCode(),
                destination.getCurrency(),
                destination.getHotelPrice());
    }

    public List<DestinationDTOForGet> hotelResultDTOList(final List<Destination> destinations) {
        return destinations.stream()
                .map(this::mapToDestinationResultDTO)
                .toList();
    }


}
