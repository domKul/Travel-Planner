package travelplanner.destination;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import travelplanner.destination.query.SimpleDestinationQueryDto;
import travelplanner.jpa.AbstractRepository;

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
        Destination destinationOrElseThrow = getDestination(hotelId);
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
        Destination destinationOrElseThrow = getDestination(hotelId);
        return destinationMapper.mapToDestinationResultDTO(destinationOrElseThrow);
    }
    public SimpleDestinationQueryDto getDestinationOrElseThrow(long id) {
        Destination entity = getDestination(id);
        return entity.mapToSimpleQueryDto();
    }

    private Destination getDestination(long id) {
        return findEntity(destinationRepository, id);
    }
}
