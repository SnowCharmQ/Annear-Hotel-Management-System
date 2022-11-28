package sustech.hotel.model.vo.hotel;

import lombok.Data;
import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;

import java.util.List;
import java.util.Map;

@Data
public class RoomTypeSearchVo {
    private List<AvailableRoomTypeTo> roomTypes;
    private Map<Long, List<String>> roomTypeImages;
}
