package com.planner.travelplanner.booking;

import com.planner.travelplanner.customer.CustomerService;
import com.planner.travelplanner.destination.DestinationService;
import com.planner.travelplanner.enums.ExceptionMessages;
import com.planner.travelplanner.exception.AlreadyExistException;
import com.planner.travelplanner.exception.NotFoundException;
import com.planner.travelplanner.jpa.AbstractRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService extends AbstractRepository<BookingRepository, Booking> {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final CustomerService customerService;
    private final DestinationService destinationService;
    private final List<BookingObserver> observers = new ArrayList<>();


    BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper,
                   CustomerService customerService, DestinationService destinationService) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.customerService = customerService;
        this.destinationService = destinationService;
    }

    void addObserver(BookingObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Booking booking) {
        for (BookingObserver observer : observers) {
            observer.notifyBookingAdded(booking);
        }
    }

    Booking addBooking(final BookingDTOCreate bookingDTOCreate) {
        boolean isValid = Optional.ofNullable(bookingDTOCreate).isEmpty();
        if (isValid) {
            throw new NotFoundException(ExceptionMessages.BODY_IS_NULL);
        }
        isDestinationExist(bookingDTOCreate);
        var booking = createBooking(bookingDTOCreate);
        LOGGER.info("Email sent");
        Booking save = bookingRepository.save(booking);
        notifyObservers(booking);
        return save;
    }

    private Booking createBooking(BookingDTOCreate bookingDTOCreate) {
        var booking = new Booking();
        var customerOrThrow = customerService.findCustomerOrThrow(bookingDTOCreate.getCustomerId());
        var destinationOrElseThrow = destinationService.getDestinationOrElseThrow(bookingDTOCreate.getDestinationId());
        booking.setCustomer(customerOrThrow);
        booking.setStartDate(bookingDTOCreate.getStartDate());
        booking.setEndDate(bookingDTOCreate.getEndDate());
        booking.setDestinations(destinationOrElseThrow);
        return booking;
    }

    private void isDestinationExist(BookingDTOCreate bookingDTOCreate) {
        boolean isExist = bookingRepository
                .existsByDestination_DestinationIdAndCustomer_CustomerId(bookingDTOCreate
                        .getDestinationId(), bookingDTOCreate.getCustomerId());
        if (isExist) {
            throw new AlreadyExistException(ExceptionMessages.BOOKING_ALREADY_EXIST);
        }
    }

    List<BookingDTOGet> showAllBookings() {
        List<Booking> allBookings = bookingRepository.findAll();
        if (allBookings.isEmpty()) {
            return new ArrayList<>();
        }
        return bookingMapper.mapToDTOListGet(allBookings);
    }

    BookingDTOGet showBookingById(final long bookingId) {
        var entity = findEntity(bookingRepository, bookingId);
        return bookingMapper.mapToBookingDTOGet(entity);
    }

    BookingDTO modifyBooking(final long bookingId, final BookingDTOCreate bookingDTOCreate) {
        if (bookingRepository.existsById(bookingId)) {
            Booking booking = mapToBookingForUpdate(bookingId, bookingDTOCreate);
            Booking saveUpdatedBooking = bookingRepository.save(booking);
            return bookingMapper.mapToBookingDTO(saveUpdatedBooking);
        } else {
            throw new NotFoundException(ExceptionMessages.ENTITY_NOT_FOUND);
        }
    }

    void deleteBookingById(final long bookingId) {
        Booking entity = findEntity(bookingRepository, bookingId);
        bookingRepository.deleteById(entity.getBookingId());
        LOGGER.info("Booking deleted");
    }

    Booking mapToBookingForUpdate(final long bookingId, BookingDTOCreate bookingDTOCreate) {
        var bookingEntity = findEntity(bookingRepository, bookingId);
        var customerOrThrow = customerService.findCustomerOrThrow(bookingDTOCreate.getCustomerId());
        var destinationOrElseThrow = destinationService.getDestinationOrElseThrow(bookingDTOCreate.getDestinationId());
        bookingEntity.setStartDate(bookingDTOCreate.getStartDate());
        bookingEntity.setEndDate(bookingDTOCreate.getEndDate());
        bookingEntity.setCustomer(customerOrThrow);
        bookingEntity.setDestinations(destinationOrElseThrow);
        return bookingEntity;
    }
}
