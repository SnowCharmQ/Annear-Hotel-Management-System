package sustech.hotel.model.vo.hotel;

import lombok.Data;

import java.util.Date;

@Data
public class CommentShowVo {
    private Long typeId;
    private String typeName;
    private String hotelName;
    private String comments;
    private Date commentTime;
    private String picture;
    private String video;
    private Integer score;
}
