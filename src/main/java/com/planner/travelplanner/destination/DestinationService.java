package com.planner.travelplanner.destination;

import com.planner.travelplanner.jpa.AbstractRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinationService extends AbstractRepository<DestinationRepository, Destination> {
    private final DestinationMapper destinationMapper;
    private final DestinationRepository destinationRepository;

    DestinationService(DestinationMapper destinationMapper, DestinationRepository destinationRepository) {
        this.destinationMapper = destinationMapper;
        this.destinationRepository = destinationRepository;
    }
     void saveDestination(Destination destination){
        destinationRepository.save(destination);
    }

    @Transactional
    public void deleteDestinationById(long hotelId) {
        Destination destinationOrElseThrow = getDestinationOrElseThrow(hotelId);
        destinationRepository.delete(destinationOrElseThrow);
    }

    List<DestinationDTOForGet> getAllDestinationsInDB() {
        List<Destination> all = destinationRepository.findAll();
        if (!all.isEmpty()){
        return destinationMapper.hotelResultDTOList(all);
        }
        return new ArrayList<>();
    }

    DestinationDTOForGet getDestinationById(long hotelId) {
        Destination destinationOrElseThrow = getDestinationOrElseThrow(hotelId);
        return destinationMapper.mapToDestinationResultDTO(destinationOrElseThrow);
    }
    public Destination getDestinationOrElseThrow(long id) {
        return findEntity(destinationRepository, id);
    }
}
