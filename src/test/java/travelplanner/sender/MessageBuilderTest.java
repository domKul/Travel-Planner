package travelplanner.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import travelplanner.booking.query.SimpleBookingQueryDto;
import travelplanner.customer.query.SimpleCustomerQueryDto;
import travelplanner.destination.query.SimpleDestinationQueryDto;

import java.util.Date;

@SpringBootTest
class MessageBuilderTest {
    @Autowired
    private MessageBuilder messageBuilder;

    @Test
    void shouldBuildMessageToSendByEmail() {
        //Given
        SimpleBookingQueryDto bookingQueryDto = new SimpleBookingQueryDto(1L, new Date(), new Date(), new SimpleCustomerQueryDto(), new SimpleDestinationQueryDto());
        //When
        StringBuilder content = messageBuilder.createContent(bookingQueryDto);
        //Then
        Assertions.assertNotNull(content);
    }
}
