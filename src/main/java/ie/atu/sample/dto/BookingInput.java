package ie.atu.sample.dto;


public record BookingInput(
        Long id,
        String organiserName,
        int attendeeCount,
        Long roomId
) {

}
