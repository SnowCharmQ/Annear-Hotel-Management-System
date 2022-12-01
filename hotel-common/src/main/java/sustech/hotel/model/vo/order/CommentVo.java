package sustech.hotel.model.vo.order;

import lombok.Data;

@Data
public class CommentVo {
    private String orderId;
    private Long typeId;
    private String comments;
    private String commentTime;
    private String picture;
    private String video;
    private Integer score;
}
