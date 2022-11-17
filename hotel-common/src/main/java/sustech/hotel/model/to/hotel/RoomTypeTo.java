package sustech.hotel.model.to.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeTo {
    private Long typeId;
    private Long hotelId;
    private BigDecimal price;
    private Long breakfast;
    private Long windows;
    private Long television;
    private Long bathtub;
    private Long thermos;
}
