package sustech.hotel.room.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
@TableName("chr_room")
public class RoomEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer roomId;
    private Integer hotelId;
    private Integer roomNumber;
    private Integer typeId;
    private Integer layoutId;
}