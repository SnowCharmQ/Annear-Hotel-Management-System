package sustech.hotel.model.to.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTo {
    private Integer roomId;
    private Integer hotelId;
    private Integer roomNumber;
    private Integer typeId;
    private Integer layoutId;
}
