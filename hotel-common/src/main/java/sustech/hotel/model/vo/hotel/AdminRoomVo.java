package sustech.hotel.model.vo.hotel;

import lombok.Data;

import java.util.List;

@Data
public class AdminRoomVo {
    private Long hotelId;
    private String hotelName;
    private Integer floors;
    private List<String> typeNames;
}
