package com.planner.travelplanner.scheduler;

import com.planner.travelplanner.domain.Booking;
import com.planner.travelplanner.repository.BookingRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class ExpiredBookingsRemoveScheduler {

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
