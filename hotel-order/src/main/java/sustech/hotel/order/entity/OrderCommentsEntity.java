package sustech.hotel.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
@TableName("cho_order_comments")
public class OrderCommentsEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String orderId;
    private String comments;
    private Date commentTime;
}