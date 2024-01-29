package travelplanner.destination;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
class DestinationMapper {

    DestinationDTOForGet mapToDestinationResultDTO(Destination destination) {
        return new DestinationDTOForGet(destination.getDestinationId(),
                destination.getIdName(),
                destination.getName(),
                destination.getCountryCode(),
                destination.getCurrency(),
                destination.getDestinationPrice());
    }

    List<DestinationDTOForGet> hotelResultDTOList(final List<Destination> destinations) {
        return destinations.stream()
                .map(this::mapToDestinationResultDTO)
                .toList();
    }
}
