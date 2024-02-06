package travelplanner.sender;

import org.springframework.stereotype.Component;
import travelplanner.booking.query.SimpleBookingQueryDto;

@Component
class MessageBuilder {

    StringBuilder createContent(SimpleBookingQueryDto booking) {
        StringBuilder content = new StringBuilder();
        content.append("Start Date ");
        content.append(booking.getStartDate());
        content.append("\n");
        content.append("End Date ");
        content.append(booking.getEndDate());
        content.append("\n");
        content.append("Traveler first name and last name : \n");
        content.append(booking.getCustomer().getFirstName());
        content.append("\n");
        content.append(booking.getCustomer().getLastName());
        content.append("\n");
        content.append("Destination info: \n");
        content.append("Destination name: ").append(booking.getDestination().getName());
        content.append("\n");
        content.append("Destination country: ").append(booking.getDestination().getCountryCode());
        content.append("\n");
        content.append("Destination price: ").append(booking.getDestination().getDestinationPrice()).append(booking.getDestination().getCurrency());
        content.append("\n");
        content.append("Destination id: ").append(booking.getDestination().getIdName());
        return content;
    }
}
