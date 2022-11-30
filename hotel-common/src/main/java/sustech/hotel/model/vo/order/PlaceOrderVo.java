package sustech.hotel.model.vo.order;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class PlaceOrderVo {
    private String userToken;
    private String orderToken;
    private Long roomId;
    private String startDate;
    private String endDate;
    private String additional;
    private List<String> guestInfo;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
}
