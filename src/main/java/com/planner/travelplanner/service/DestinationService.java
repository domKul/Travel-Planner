package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.dto.destination.DestinationDTOForGet;
import com.planner.travelplanner.domain.exception.HotelNotFoundException;
import com.planner.travelplanner.mapper.DestinationMapper;
import com.planner.travelplanner.repository.DestinationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {
    private final DestinationMapper destinationMapper;
    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationMapper destinationMapper, DestinationRepository destinationRepository) {
        this.destinationMapper = destinationMapper;
        this.destinationRepository = destinationRepository;
    }

    @Transactional
    public void deleteDestinationById(long hotelId) {
        if (destinationRepository.existsById(hotelId)) {
            destinationRepository.deleteById(hotelId);
        } else {
            throw new HotelNotFoundException();
        }
    }

    public List<DestinationDTOForGet> getAllDestinationsInDB() {
        return destinationMapper.hotelResultDTOList(destinationRepository.findAll());
    }

    public DestinationDTOForGet getDestinationById(long hotelId) {
        if (destinationRepository.existsById(hotelId)) {
            return destinationMapper.mapToDestinationResultDTO(destinationRepository.findById(hotelId).get());
        } else {
            throw new HotelNotFoundException();
        }
    }


}
