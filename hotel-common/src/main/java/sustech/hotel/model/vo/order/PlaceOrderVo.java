package sustech.hotel.model.vo.order;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class PlaceOrderVo {
    private String userToken;
    private String orderToken;
    private Long roomID;
    private String startDate;
    private String endDate;
    private List<String> guestInfo;
}
