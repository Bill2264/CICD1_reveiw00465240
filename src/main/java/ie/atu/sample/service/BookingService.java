package ie.atu.sample.service;

import ie.atu.sample.dto.BookingInput;
import ie.atu.sample.dto.BookingResponse;
import ie.atu.sample.model.Booking;
import ie.atu.sample.model.Room;
import ie.atu.sample.repository.BookingRepository;
import ie.atu.sample.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingService {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public BookingService(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(BookingInput request) {
        if(request.roomId() == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room id required");
        }
        Room room = roomRepository.
                findById(request.roomId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Room does not exist"));

        if(request.organiserName() == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Organiser Name required");
        }
        if(request.attendeeCount() <= 0)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Attendee count must be greater than 0");
        }
        if(request.attendeeCount() > room.getCapacity())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room capacity exceeded");
        }

        Booking booking = new Booking();
        booking.setOrganiserName(request.organiserName());
        booking.setAttendeeCount(request.attendeeCount());
        booking.setRoom(room);

        return bookingRepository.save(booking);
    }

    public BookingResponse readBooking(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Booking id is required");
        }
        Booking booking = bookingRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Booking not found"));

        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setId(booking.getId());
        bookingResponse.setOrganizerName(booking.getOrganiserName());
        bookingResponse.setAttendeeCount(booking.getAttendeeCount());
        bookingResponse.setRoom(booking.getRoom());
        bookingResponse.setStatus("Active");
        return bookingResponse;
    }

    public List<Booking> getBookingByRoomName(String roomName) {
        return bookingRepository.getBookingsByRoom_Name(roomName);
    }
}
