package sustech.hotel.model.to.hotel;

import lombok.Data;

@Data
public class CommentInfoTo {
    private Long typeId;
    private String typeName;
    private Long hotelId;
    private String hotelName;
    private String typePicture;
}
