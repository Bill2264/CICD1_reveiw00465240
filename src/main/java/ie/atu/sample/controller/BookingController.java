package ie.atu.sample.controller;

import ie.atu.sample.dto.BookingInput;
import ie.atu.sample.dto.BookingResponse;
import ie.atu.sample.model.Booking;
import ie.atu.sample.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingInput bookingInput) {
        return bookingService.createBooking(bookingInput);
    }

    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable Long id){
        return bookingService.readBooking(id);
    }

    @GetMapping
    public List<Booking> getBookingByRoomName(String roomName){
        return bookingService.getBookingByRoomName(roomName);
    }
}
