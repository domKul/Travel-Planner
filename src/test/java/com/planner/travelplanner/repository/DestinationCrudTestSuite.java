package com.planner.travelplanner.repository;

import com.planner.travelplanner.domain.Destination;
import com.planner.travelplanner.mapper.IdType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class DestinationCrudTestSuite {

    @Autowired
    private DestinationRepository destinationRepository;

    private Destination destination1 = null;
    private Destination destination2 = null;

    private void dataForTests(){
        destination1 = new Destination(IdType.EMPTY_ID.getId(),IdType.EMPTY_ID.getId(),"null","null","null", 233);
        destination2 = new Destination(IdType.EMPTY_ID.getId(),IdType.EMPTY_ID.getId(),"null","null","null", 2233);
    }

    @Test
    public void shouldCreteAndSaveHotel(){
        //Given
        dataForTests();

        //When
        Destination saveDestination1 = destinationRepository.save(destination1);
        Destination saveDestination2 = destinationRepository.save(destination2);

        boolean result = saveDestination1.equals(saveDestination2);

        //Then
        assertFalse(result);
        assertEquals(2, destinationRepository.count());

        //CleanUp
        long getId1 = saveDestination1.getDestinationId();
        long getId2 = saveDestination2.getDestinationId();
        destinationRepository.deleteById(getId1);
        destinationRepository.deleteById(getId2);
    }

    @Test
    public void shouldDeleteHotelById(){
        //Given
        dataForTests();

        //When
        Destination saveDestination1 = destinationRepository.save(destination1);
        Destination saveDestination2 = destinationRepository.save(destination2);

        long getId1 = saveDestination1.getDestinationId();
        long getId2 = saveDestination2.getDestinationId();
        int hash1 = destination1.hashCode();
        int hash2 = destination2.hashCode();

        destinationRepository.deleteById(getId1);
        destinationRepository.deleteById(getId2);

        //Then
        assertNotEquals(hash1, destination2.hashCode());
        assertNotEquals(hash2, destination1.hashCode());
        assertEquals(0, destinationRepository.count());
    }

    @Test
    public void shouldFindHotelById(){
        //Given
        dataForTests();

        //When
        Destination saveDestination1 = destinationRepository.save(destination1);
        Destination saveDestination2 = destinationRepository.save(destination2);

        long getId1 = saveDestination1.getDestinationId();
        long getId2 = saveDestination2.getDestinationId();

        //Then
        assertEquals(destination1.getDestinationId(),getId1);
        assertEquals(destination2.getDestinationId(),getId2);

        //CleanUp
        destinationRepository.deleteById(getId1);
        destinationRepository.deleteById(getId2);
    }

    @Test
    public void shouldModifyHotel(){
        //Given
        dataForTests();

        //When
        Destination saveDestination1 = destinationRepository.save(destination1);
        saveDestination1.setCurrency("PLN");
        saveDestination1.setCountryCode("64-000");
        destinationRepository.save(saveDestination1);

        //Then
        assertEquals("PLN", saveDestination1.getCurrency());
        assertEquals("64-000", saveDestination1.getCountryCode());

        //CleanUp
        long getId = destination1.getDestinationId();
        destinationRepository.deleteById(getId);

    }

}