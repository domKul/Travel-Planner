package com.planner.travelplanner.domain.dto.booking;

import java.util.Date;

public class BookingDTOCreate {
    private Date startDate;

    private Date endDate;

    private long customerId;



    private long hotelId;

    public BookingDTOCreate(Date startDate, Date endDate, long customerId, long hotelId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
        this.hotelId = hotelId;
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

    public long getHotelId() {
        return hotelId;
    }

    public static class Builder{
        private Date startDate;
        private Date endDate;
        private long customerId;
        private long hotelId;

        public Builder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }
        public Builder endDate(Date endDate){
            this.endDate = endDate;
            return this;
        }
        public Builder customerId(long customerId){
            this.customerId=customerId;
            return this;
        }
        public Builder hotelId(long hotelId){
            this.hotelId = hotelId;
            return this;
        }
        public BookingDTOCreate build(){
            return new BookingDTOCreate(startDate,endDate,customerId,hotelId);
        }
    }

}
