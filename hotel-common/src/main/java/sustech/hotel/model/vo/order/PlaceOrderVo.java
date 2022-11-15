package sustech.hotel.model.vo.order;

import lombok.Data;

import java.sql.Date;

@Data
public class PlaceOrderVo {
    private Long userId;
    private Long roomID;
    private Date startDate;
    private Date endDate;
}
