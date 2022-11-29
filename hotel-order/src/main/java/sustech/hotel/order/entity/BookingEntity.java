package sustech.hotel.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
@TableName("cho_booking")
public class BookingEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String orderId;
    private Long hotelId;
    private Long typeId;
    private Long roomId;
    private Date startDate;
    private Date endDate;
    private Integer orderState;
}