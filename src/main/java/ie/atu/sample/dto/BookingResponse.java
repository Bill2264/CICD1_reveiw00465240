package ie.atu.sample.dto;

import ie.atu.sample.model.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingResponse {
    private Long id;
    private String organizerName;
    private int attendeeCount;
    private Room room;
    private String status;
}
