package sustech.hotel.room.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
@TableName("chr_room_type_picture")
public class RoomTypePictureEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long typeId;
    private String picturePath;
    private Integer cover;
}