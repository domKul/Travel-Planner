package com.planner.travelplanner.destination;

import com.planner.travelplanner.exception.HotelNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {
    private final DestinationMapper destinationMapper;
    private final DestinationRepository destinationRepository;

    DestinationService(DestinationMapper destinationMapper, DestinationRepository destinationRepository) {
        this.destinationMapper = destinationMapper;
        this.destinationRepository = destinationRepository;
    }

    public Destination getDestinationOrElseThrow(long id){
        return destinationRepository.findById(id)
                .orElseThrow(HotelNotFoundException::new);
    }

    @Transactional
    public void deleteDestinationById(long hotelId) {
        if (destinationRepository.existsById(hotelId)) {
            destinationRepository.deleteById(hotelId);
        } else {
            throw new HotelNotFoundException();
        }
    }

    List<DestinationDTOForGet> getAllDestinationsInDB() {
        return destinationMapper.hotelResultDTOList(destinationRepository.findAll());
    }

    DestinationDTOForGet getDestinationById(long hotelId) {
        if (destinationRepository.existsById(hotelId)) {
            return destinationMapper.mapToDestinationResultDTO(destinationRepository.findById(hotelId).get());
        } else {
            throw new HotelNotFoundException();
        }
    }
}
