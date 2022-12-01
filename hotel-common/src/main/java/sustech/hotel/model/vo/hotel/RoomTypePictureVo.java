package sustech.hotel.model.vo.hotel;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RoomTypePictureVo{
    private Long typeId;
    private String picturePath;
    private Integer cover;
}