package sustech.hotel.model.vo.order;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVo {
    private Long typeId;
    private String comments;
    private Date commentTime;
    private String picture;
    private String video;
}
