package sustech.hotel.model.vo.hotel;

import lombok.Data;

import java.sql.Date;

@Data
public class AvailableRoomTypeVo {
    private Date startDate;
    private Date endDate;
    private Long hotelId;
}
