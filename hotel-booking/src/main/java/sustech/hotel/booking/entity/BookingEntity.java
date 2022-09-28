package sustech.hotel.booking.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
@TableName("chb_booking")
public class BookingEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String orderId;
    private Long roomId;
    private Date startTime;
    private Date endTime;
}