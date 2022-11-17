package sustech.hotel.model.to.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTo {
    private Long roomId;
    private Long hotelId;
    private Long roomNumber;
    private Long typeId;
    private Long layoutId;
}
