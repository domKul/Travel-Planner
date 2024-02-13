package travelplanner.booking;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

class BookingDTOCreate {
    @NotNull(message = "Start Date required")
    private final Date startDate;
    @NotNull(message = "End Date required")
    private final Date endDate;
    @NotNull(message = "Customer ID required")
    private final Long customerId;
    @NotNull(message = "Destination ID required")
    private final Long destinationId;

    BookingDTOCreate(Date startDate, Date endDate, long customerId, long destinationId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
        this.destinationId = destinationId;
    }

    Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    long getCustomerId() {
        return customerId;
    }

    long getDestinationId() {
        return destinationId;
    }

    public static class Builder {
        private Date startDate;
        private Date endDate;
        private long customerId;
        private long destinationId;

        public Builder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder customerId(long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder destinationId(long destinationId) {
            this.destinationId = destinationId;
            return this;
        }

        BookingDTOCreate build() {
            return new BookingDTOCreate(startDate, endDate, customerId, destinationId);
        }
    }
}
