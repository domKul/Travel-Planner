package travelplanner.location;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import travelplanner.location.LocationRestService;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LocalisationRestTemplateTestSuite {

    @Autowired
    private LocationRestService locationRestService;

    @Test
    void shouldTestUrlBuilder() {
        // Given
        String name = "New York";
        String locale = "en_US";
        // When
        URI result = locationRestService.urlBuilder(name, locale);
        // Then
        String expectedUrl = "https://booking-com.p.rapidapi.com/v1/hotels/locations?name=New%20York&locale=en_US";
        assertEquals(expectedUrl, result.toString());
    }
}
