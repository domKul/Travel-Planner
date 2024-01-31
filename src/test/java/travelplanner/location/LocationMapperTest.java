package travelplanner.location;

import org.junit.jupiter.api.BeforeEach;
import travelplanner.enums.IdType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LocationMapperTest {

    @Autowired
    private LocationMapper locationMapper;
    private LocationDTO locationDTO1;
    private LocationDTO locationDTO2;
    private Location location;
    private Location location1;

    @BeforeEach
    void dataForTests(){
        locationDTO1 = new LocationDTO();
        locationDTO1.setCountry("test");
        locationDTO1.setLabel("test");
        locationDTO1.setName("test");
        locationDTO1.setRegion("test");
        locationDTO2 = new LocationDTO();
        locationDTO2.setCountry("test2");
        locationDTO2.setLabel("test2");
        locationDTO2.setName("test2");
        locationDTO2.setRegion("test");
        location = new Location(IdType.EMPTY_ID.getId(), "string", "string", "123", "region", "name", "country", 12, "null");
        location1 = new Location(IdType.EMPTY_ID.getId(), "string1", "string1", "1231", "region", "name", "country", 12, "null");

    }

    @Test
    void shouldMapToLocation() {
        //Given

        //When
        Location mapping = locationMapper.mapToLocation(locationDTO1);
        //Then
        assertEquals("test", mapping.getCountry());
        assertEquals(locationDTO1.getHotels(), mapping.getHotels());
        assertEquals(locationDTO1.getRegion(), mapping.getRegion());
        assertEquals(locationDTO1.getTimezone(), mapping.getTimezone());
        assertEquals(locationDTO1.getName(), mapping.getName());
    }

    @Test
    void shouldMapToLocationList() {
        //Given
        List<LocationDTO> dtoList = Arrays.asList(locationDTO1, locationDTO2);
        //When
        List<Location> mapping = locationMapper.mapToLocationListFromDTO(dtoList);
        //Then
        assertEquals(2, mapping.size());
        assertEquals("test2", mapping.get(1).getCountry());
        assertEquals("test2", mapping.get(1).getName());
        assertEquals("test2", mapping.get(1).getLabel());
        assertEquals("test", mapping.get(0).getLabel());
        assertEquals("test", mapping.get(0).getName());
        assertEquals("test", mapping.get(0).getCountry());
    }

    @Test
    void shouldMapToLocationDTO() {
        //Given
        //When
        LocationDTO mappingToDto = locationMapper.mapToLocationDTO(location);
        //Then
        assertEquals(location.getLabel(), mappingToDto.getLabel());
        assertEquals(location.getDest_id(), mappingToDto.getDest_id());
        assertEquals(location.getTimezone(), mappingToDto.getTimezone());
    }

    @Test
    void shouldMapListToDto(){
        //Given
        List<Location> locationList = List.of(location,location1);
        //When
        List<LocationDTO> toDto = locationMapper.mapToLocationListDTO(locationList);
        //Then
        assertEquals(LocationDTO.class,toDto.get(0).getClass());
        assertEquals(LocationDTO.class,toDto.get(1).getClass());
    }
}
