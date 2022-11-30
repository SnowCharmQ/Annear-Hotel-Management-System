package sustech.hotel.model.vo.order;
import lombok.Data;
import sustech.hotel.model.vo.member.UserVo;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class OrderConfirmRespVo {
    private String hotelName;
    private String province;
    private String city;
    private String district;
    private String detailAddress;
    private Long roomId;
    private Long hotelId;
    private Long roomNumber;
    private Long typeId;
    private Long layoutId;
    private Integer upperLimit;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Integer breakfast;
    private BigDecimal finalPrice;
    private String token;
    private Date startDate;
    private Date endDate;
    private UserVo user;
}
