package travelplanner.destination;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DestinationHttpClientConfigTest {

    @Autowired
    private DestinationHttpClientConfig destinationHttpClientConfig;

    @Test
    void testUrlBuilder() {
        // Given
        String orderedBy = "price";
        int adultsNumber = 2;
        String checkinDate = "2023-06-01";
        String filterByCurrency = "USD";
        int destId = 1234;
        String locale = "en_US";
        String checkoutDate = "2023-06-05";
        String units = "metric";
        int roomNumber = 1;
        String destType = "city";
        // When
      URI result = destinationHttpClientConfig.urlBuilder(orderedBy, adultsNumber, checkinDate, filterByCurrency, destId, locale, checkoutDate, units, roomNumber, destType);
        // Then
        String expectedUrl = "https://booking-com.p.rapidapi.com/v2/hotels/search?order_by=price&adults_number=2&checkin_date=2023-06-01&filter_by_currency=USD&dest_id=1234&locale=en_US&checkout_date=2023-06-05&units=metric&room_number=1&dest_type=city";
        assertEquals(expectedUrl, result.toString());
    }

}
