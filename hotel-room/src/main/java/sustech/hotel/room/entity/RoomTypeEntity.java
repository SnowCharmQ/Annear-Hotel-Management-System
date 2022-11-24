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
    private Integer upperLimit;
    private BigDecimal price;
    private Boolean breakfast;
    private Integer windows;
    private Integer television;
    private Integer bathtub;
    private Integer thermos;
}