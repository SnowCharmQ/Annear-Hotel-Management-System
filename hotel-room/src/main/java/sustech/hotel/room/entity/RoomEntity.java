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
    private Long roomId;
    private Long hotelId;
    private Long roomNumber;
    private Long typeId;
    private Long layoutId;
    private Long floorPlanId;
}