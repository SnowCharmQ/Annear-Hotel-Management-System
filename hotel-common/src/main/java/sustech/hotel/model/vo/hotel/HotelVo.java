package sustech.hotel.model.vo.hotel;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("酒店信息")
public class HotelVo {
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
