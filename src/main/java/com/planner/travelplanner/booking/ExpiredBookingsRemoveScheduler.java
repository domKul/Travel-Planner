package com.planner.travelplanner.booking;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
class ExpiredBookingsRemoveScheduler {

    private final BookingRepository bookingRepository;

    public ExpiredBookingsRemoveScheduler(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Scheduled(fixedRate = 3600000)
    public void performRemoveExpiredBookings() {
        Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Booking> expiredBookings = bookingRepository.findExpiredBookings(currentDate);
        bookingRepository.deleteAll(expiredBookings);
    }
}
