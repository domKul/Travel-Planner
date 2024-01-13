package com.planner.travelplanner.booking;

import com.planner.travelplanner.customer.CustomerService;
import com.planner.travelplanner.destination.DestinationService;
import com.planner.travelplanner.enums.ExceptionMessages;
import com.planner.travelplanner.exception.NotFoundException;
import com.planner.travelplanner.jpa.AbstractRepository;
import com.planner.travelplanner.sender.EmailSenderImpl;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService extends AbstractRepository<BookingRepository,Booking> {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final CustomerService customerService;
    private final DestinationService destinationService;
    private final EmailSenderImpl emailSender;

    BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper,
                   CustomerService customerService, DestinationService destinationService, EmailSenderImpl emailSender) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.customerService = customerService;
        this.destinationService = destinationService;
        this.emailSender = emailSender;
    }


    Booking addBooking(final BookingDTOCreate bookingDTOCreate) throws MessagingException {
        boolean isValid = Optional.ofNullable(bookingDTOCreate).isEmpty();
        if (isValid) {
            throw new NotFoundException(ExceptionMessages.BODY_IS_NULL);
        }
        var booking = new Booking();
        var customerOrThrow = customerService.findCustomerOrThrow(bookingDTOCreate.getCustomerId());
        var destinationOrElseThrow = destinationService.getDestinationOrElseThrow(bookingDTOCreate.getDestinationId());
        booking.setCustomer(customerOrThrow);
            booking.setStartDate(bookingDTOCreate.getStartDate());
            booking.setEndDate(bookingDTOCreate.getEndDate());
            booking.setDestinations(destinationOrElseThrow);
        emailSender.sendEmail(customerOrThrow.getEmail(),booking);
        LOGGER.info("Email was sent");
        return bookingRepository.save(booking);
    }

    List<BookingDTOGet> showAllBookings() {
        List<Booking> allBookings = bookingRepository.findAll();
        if (allBookings.isEmpty()){
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
