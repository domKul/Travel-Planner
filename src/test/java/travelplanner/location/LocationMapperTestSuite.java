package travelplanner.location;

import travelplanner.enums.IdType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import travelplanner.location.Location;
import travelplanner.location.LocationDTO;
import travelplanner.location.LocationMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LocationMapperTestSuite {

    @Autowired
    private LocationMapper locationMapper;
    private LocationDTO locationDTO1;
    private LocationDTO locationDTO2;
    private Location location;

    @Test
    void shouldMapToLocation() {
        //Given
        locationDTO1 = new LocationDTO();
        locationDTO1.setCountry("test");
        locationDTO1.setLabel("test");
        locationDTO1.setName("test");
        locationDTO1.setRegion("test");
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
        locationDTO1 = new LocationDTO();
        locationDTO1.setCountry("test1");
        locationDTO1.setLabel("test1");
        locationDTO1.setName("test1");
        locationDTO1.setRegion("test1");
        locationDTO2 = new LocationDTO();
        locationDTO2.setCountry("test2");
        locationDTO2.setLabel("test2");
        locationDTO2.setName("test2");
        locationDTO2.setRegion("test");
        List<LocationDTO> dtoList = Arrays.asList(locationDTO1, locationDTO2);
        //When
        List<Location> mapping = locationMapper.mapToLocationListFromDTO(dtoList);
        //Then
        assertEquals(2, mapping.size());
        assertEquals("test2", mapping.get(1).getCountry());
        assertEquals("test2", mapping.get(1).getName());
        assertEquals("test2", mapping.get(1).getLabel());
        assertEquals("test1", mapping.get(0).getLabel());
        assertEquals("test1", mapping.get(0).getName());
        assertEquals("test1", mapping.get(0).getCountry());
    }

    @Test
    void shouldMapToLocationDTO() {
        //Given
        location = new Location(IdType.EMPTY_ID.getId(), "string", "string", "123", "region", "name", "country", 12, "null");
        //When
        LocationDTO mappingToDto = locationMapper.mapToLocationDTO(location);
        //Then
        assertEquals(location.getLabel(), mappingToDto.getLabel());
        assertEquals(location.getDest_id(), mappingToDto.getDest_id());
        assertEquals(location.getTimezone(), mappingToDto.getTimezone());
    }
}
