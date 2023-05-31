package com.planner.travelplanner.domain.dto.booking;

import java.util.Date;

public class BookingDTOCreate {
    private final Date startDate;

    private final Date endDate;

    private final Long customerId;

    private final Long destinationId;

    public BookingDTOCreate(Date startDate, Date endDate, long customerId, long destinationId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
        this.destinationId = destinationId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public long getCustomerId() {
        return customerId;
    }

    public long getDestinationId() {
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

        public BookingDTOCreate build() {
            return new BookingDTOCreate(startDate, endDate, customerId, destinationId);
        }
    }

}
