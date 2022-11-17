package sustech.hotel.room.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
@TableName("chr_room_type")
public class RoomTypeEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private Long typeId;
    private Long hotelId;
    private BigDecimal price;
    private Long breakfast;
    private Long windows;
    private Long television;
    private Long bathtub;
    private Long thermos;
}