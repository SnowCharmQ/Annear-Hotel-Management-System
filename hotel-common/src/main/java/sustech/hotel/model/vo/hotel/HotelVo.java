package sustech.hotel.model.vo.hotel;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("酒店信息")
public class HotelVo {
    private Integer hotelId;
    private String province;
    private String city;
    private String district;
    private String hotelName;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String telephone;
    private Integer swimmingPool;
    private Integer gym;
    private Integer diningRoom;
    private Integer bar;
    private Integer parking;
    private Integer spa;
    private Integer chessRoom;
    private Integer starLevel;
}
