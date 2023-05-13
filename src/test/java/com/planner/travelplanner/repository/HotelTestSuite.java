package com.planner.travelplanner.repository;

import com.planner.travelplanner.domain.Hotel;
import com.planner.travelplanner.mapper.IdType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class HotelTestSuite {

    @Autowired
    private HotelRepository hotelRepository;

    private Hotel hotel1 = null;
    private Hotel hotel2 = null;

    private void dataForTests(){
        hotel1 = new Hotel(IdType.EMPTY_ID.getId(),1,null,null,null,null,null,null,null,null,null,null,null);
        hotel2 = new Hotel(IdType.EMPTY_ID.getId(),2,null,null,null,null,null,null,null,null,null,null,null);
    }

    @Test
    public void shouldCreteAndSaveHotel(){
        //Given
        dataForTests();

        //When
        Hotel saveHotel1 = hotelRepository.save(hotel1);
        Hotel saveHotel2 = hotelRepository.save(hotel2);

        boolean result = saveHotel1.equals(saveHotel2);

        //Then
        assertFalse(result);
        assertEquals(2,hotelRepository.count());

        //CleanUp
        long getId1 = saveHotel1.getHotelId();
        long getId2 = saveHotel2.getHotelId();
        hotelRepository.deleteById(getId1);
        hotelRepository.deleteById(getId2);
    }

    @Test
    public void shouldDeleteHotelById(){
        //Given
        dataForTests();

        //When
        Hotel saveHotel1 = hotelRepository.save(hotel1);
        Hotel saveHotel2 = hotelRepository.save(hotel2);

        long getId1 = saveHotel1.getHotelId();
        long getId2 = saveHotel2.getHotelId();
        int hash1 = hotel1.hashCode();
        int hash2 = hotel2.hashCode();

        hotelRepository.deleteById(getId1);
        hotelRepository.deleteById(getId2);

        //Then
        assertNotEquals(hash1, hotel2.hashCode());
        assertNotEquals(hash2, hotel1.hashCode());
        assertEquals(0, hotelRepository.count());
    }

    @Test
    public void shouldFindHotelById(){
        //Given
        dataForTests();

        //When
        Hotel saveHotel1 = hotelRepository.save(hotel1);
        Hotel saveHotel2 = hotelRepository.save(hotel2);

        long getId1 = saveHotel1.getHotelId();
        long getId2 = saveHotel2.getHotelId();

        //Then
        assertEquals(hotel1.getHotelId(),getId1);
        assertEquals(hotel2.getHotelId(),getId2);

        //CleanUp
        hotelRepository.deleteById(getId1);
        hotelRepository.deleteById(getId2);
    }

    @Test
    public void shouldModifyHotel(){
        //Given
        dataForTests();

        //When
        Hotel saveHotel1 = hotelRepository.save(hotel1);
        saveHotel1.setCheckinDate("2023.02.12");
        saveHotel1.setCountryCode("64-000");
        hotelRepository.save(saveHotel1);

        //Then
        assertEquals("2023.02.12",saveHotel1.getCheckinDate());
        assertEquals("64-000",saveHotel1.getCountryCode());

        //CleanUp
        long getId = hotel1.getHotelId();
        hotelRepository.deleteById(getId);

    }

}