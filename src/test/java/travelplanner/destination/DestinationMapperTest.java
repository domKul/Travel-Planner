package travelplanner.destination;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DestinationMapperTest {

    @Autowired
    private DestinationMapper destinationMapper;
    private Destination destination1;
    private Destination destination2;
    private Destination destination3;

    @BeforeEach
    void dataForTests(){
        destination1 = new Destination(12,123123,"name1","countrycode1","PLN",231231);
        destination2 = new Destination(1231,123123,"name2","countrycode2","PLN",1231);
        destination3 = new Destination(2,222,"name3","countrycode3","PLN",23131);
    }

    @Test
    void shouldMapToDestinationDTOForGet(){
        //Given

        //When
        DestinationDTOForGet toDtoForGet = destinationMapper.mapToDestinationResultDTO(destination1);

        //Then
        assertNotNull(toDtoForGet);
        assertEquals(DestinationDTOForGet.class,toDtoForGet.getClass());
        assertEquals(destination1.getDestinationId(),toDtoForGet.getHotelId());
        assertEquals(destination1.getDestinationPrice(),toDtoForGet.getHotelPrice());
        assertEquals(destination1.getName(),toDtoForGet.getName());
        assertEquals(destination1.getCountryCode(),toDtoForGet.getCountryCode());
        assertEquals(destination1.getCurrency(),toDtoForGet.getCurrency());
    }

    @Test
    void shouldMapToListDestinationDTOForGet(){
        //Given
        List<Destination> destinationList = List.of(destination1, destination2, destination3);
        //When
        List<DestinationDTOForGet> toDtoList = destinationMapper.hotelResultDTOList(destinationList);
        //Then
        assertEquals(DestinationDTOForGet.class,toDtoList.get(0).getClass());
        assertEquals(DestinationDTOForGet.class,toDtoList.get(1).getClass());
        assertEquals(DestinationDTOForGet.class,toDtoList.get(2).getClass());
    }
}
