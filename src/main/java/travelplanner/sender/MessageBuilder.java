package travelplanner.sender;

import org.springframework.stereotype.Component;
import travelplanner.booking.Booking;
@Component
class MessageBuilder {

    StringBuilder createContent(Booking booking) {
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
        content.append("Destination name: ").append(booking.getDestinations().getName());
        content.append("\n");
        content.append("Destination country: ").append(booking.getDestinations().getCountryCode());
        content.append("\n");
        content.append("Destination price: ").append(booking.getDestinations().getDestinationPrice()).append(booking.getDestinations().getCurrency());
        content.append("\n");
        content.append("Destination id: ").append(booking.getDestinations().getIdName());
        return content;
    }
}
