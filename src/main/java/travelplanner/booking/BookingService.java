package travelplanner.booking;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travelplanner.booking.query.BookingDTO;
import travelplanner.booking.query.BookingDTOGet;
import travelplanner.booking.query.SimpleBookingQueryDto;
import travelplanner.customer.CustomerService;
import travelplanner.destination.DestinationService;
import travelplanner.exception.AlreadyExistException;
import travelplanner.exception.ExceptionMessages;
import travelplanner.exception.NotFoundException;
import travelplanner.jpa.AbstractRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingService extends AbstractRepository<BookingRepository, Booking> {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final CustomerService customerService;
    private final DestinationService destinationService;
    private final List<BookingObserver> observers = new ArrayList<>();

    void addObserver(BookingObserver observer) {
        observers.add(observer);
    }
    private void notifyObservers(SimpleBookingQueryDto booking) {
        observers.forEach(o->o.notifyBookingAdded(booking));
    }

    Booking addBooking(final BookingDTOCreate bookingDTOCreate) {
        boolean isValid = Optional.ofNullable(bookingDTOCreate).isEmpty();
        if (isValid ) {
            throw new NotFoundException(ExceptionMessages.BODY_IS_NULL);
        }
        isDestinationExist(bookingDTOCreate);
        var booking = new Booking();
        var customerOrThrow = customerService.findCustomerOrThrow(bookingDTOCreate.getCustomerId());
        var destinationOrElseThrow = destinationService.getDestinationOrElseThrow(bookingDTOCreate.getDestinationId());
        booking.setCustomer(customerOrThrow);
        booking.setStartDate(bookingDTOCreate.getStartDate());
        booking.setEndDate(bookingDTOCreate.getEndDate());
        booking.setDestination(destinationOrElseThrow);
        Booking save = bookingRepository.save(booking);
        notifyObservers(save.toSimpleQuery());
        LOGGER.info("Email sent");
        return save;
    }

    private void isDestinationExist(BookingDTOCreate bookingDTOCreate) {
        boolean isExist = bookingRepository
                .existsByDestination_DestinationIdAndCustomer_CustomerId(bookingDTOCreate
                        .getDestinationId(), bookingDTOCreate.getCustomerId());
        if (isExist){
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
        if (!bookingRepository.existsById(bookingId)) {
            throw new NotFoundException(ExceptionMessages.ENTITY_NOT_FOUND);
        }
            Booking booking = mapToBookingForUpdate(bookingId, bookingDTOCreate);
            Booking saveUpdatedBooking = bookingRepository.save(booking);
            return bookingMapper.mapToBookingDTO(saveUpdatedBooking);
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
        bookingEntity.setDestination(destinationOrElseThrow);
        return bookingEntity;
    }
}
