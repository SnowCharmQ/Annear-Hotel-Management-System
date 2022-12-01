package sustech.hotel.model.vo.order;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class OrderShowVo {
    private String hotelName;
    private String typeName;
    private Long roomNumber;
    private String orderId;
    private Long userId;
    private Long typeId;
    private Long roomId;
    private Integer orderStatus;
    private Date startDate;
    private Date endDate;
    private BigDecimal originMoney;
    private BigDecimal afterDiscount;
    private String additional;
    private Integer score;
    private Long hotelId;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
}
