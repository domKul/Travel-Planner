package travelplanner.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class BookingObserverManager {

    private final BookingService bookingService;
    private final EmailBookingObserver emailObserver;

    @Autowired
    public BookingObserverManager(BookingService bookingService, EmailBookingObserver emailObserver) {
        this.bookingService = bookingService;
        this.emailObserver = emailObserver;
        addObserverToBookingService();
    }

    private void addObserverToBookingService() {
        bookingService.addObserver(emailObserver);
    }
}
