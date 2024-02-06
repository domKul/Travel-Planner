package travelplanner.booking;


import travelplanner.booking.query.SimpleBookingQueryDto;

interface BookingObserver {
    void notifyBookingAdded(SimpleBookingQueryDto booking);

}