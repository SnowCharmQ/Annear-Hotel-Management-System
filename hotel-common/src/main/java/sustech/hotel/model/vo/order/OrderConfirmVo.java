package sustech.hotel.model.vo.order;

import lombok.Data;

import java.sql.Date;

@Data
public class OrderConfirmVo {
    String startDate;
    String endDate;
    String userToken;
    Long roomTypeId;
    Long roomId;
    Long hotelId;
}
