package sustech.hotel.room.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
@TableName("chr_layout")
public class LayoutEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer layoutId;
    private Integer hotelId;
    private Integer floor;
    private String planeGraphPath;
}