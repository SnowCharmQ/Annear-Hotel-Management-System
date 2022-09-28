package sustech.hotel.room.entity;

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

    private Integer typeId;
    private Integer hotelId;
    private BigDecimal price;
    private Integer breakfast;
    private Integer windows;
    private Integer television;
    private Integer bathtub;
    private Integer thermos;
}