package sustech.hotel.model.vo.hotel;

import lombok.Data;

@Data
public class RoomVo {
    private Long roomId;
    private Long hotelId;
    private Long roomNumber;
    private Long typeId;
    private String typeName;
    private Long layoutId;
    private Long floorPlanId;
}
