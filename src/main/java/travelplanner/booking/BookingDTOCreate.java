package travelplanner.booking;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
@Getter
@Builder
public class BookingDTOCreate {
    private final Date startDate;
    @NotNull(message = "End Date required")
    private final Date endDate;
    @NotNull(message = "Customer ID required")
    private final Long customerId;
    @NotNull(message = "Destination ID required")
    private final Long destinationId;

}
