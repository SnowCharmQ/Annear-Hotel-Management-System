package sustech.hotel.model.vo.hotel;

import lombok.Data;

@Data
public class ReserveReqVo {
    private Long hotelId;
    private String today;
    private String tomorrow;
}
