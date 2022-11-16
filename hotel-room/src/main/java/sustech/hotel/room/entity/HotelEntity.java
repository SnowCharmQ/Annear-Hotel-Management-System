package sustech.hotel.room.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
@TableName("chr_hotel")
public class HotelEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private Long hotelId;
    private String province;
    private String city;
    private String district;
    private String hotelName;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String telephone;
    private Long swimmingPool;
    private Long gym;
    private Long diningRoom;
    private Long bar;
    private Long parking;
    private Long spa;
    private Long chessRoom;
    private Long starLevel;
}