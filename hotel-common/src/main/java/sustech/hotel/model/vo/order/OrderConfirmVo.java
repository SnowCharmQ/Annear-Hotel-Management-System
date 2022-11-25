package sustech.hotel.model.vo.order;

import lombok.Data;

import java.sql.Date;

@Data
public class OrderConfirmVo {
    Date startDate;
    Date endDate;
    String userToken;
    Long roomTypeId;
    Long roomId;
}
