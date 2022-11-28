package sustech.hotel.model.vo.hotel;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RoomTypeSearchVo {
    private List<AvailableRoomTypeVo> roomTypes;
    private Map<Long, List<String>> roomTypeImages;
}
