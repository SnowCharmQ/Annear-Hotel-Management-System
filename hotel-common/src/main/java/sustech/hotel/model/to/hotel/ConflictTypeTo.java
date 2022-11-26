package sustech.hotel.model.to.hotel;

import lombok.Data;

import java.util.List;

@Data
public class ConflictTypeTo {
    private Long hotelId;
    private List<Long> conflictList;
}
