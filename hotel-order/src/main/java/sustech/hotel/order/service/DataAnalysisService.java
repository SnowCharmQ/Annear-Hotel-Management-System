package sustech.hotel.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.model.vo.order.DataAnalysisVo;
import sustech.hotel.order.entity.BookingEntity;
import sustech.hotel.order.entity.OrderEntity;

public interface DataAnalysisService extends IService<OrderEntity> {
    public DataAnalysisVo getLastMonthOrderNumber(Long hotelId);

    public DataAnalysisVo getLastYearRoomTypeOrderNumber(Long hotelId);

    public DataAnalysisVo getLastYearIncome(Long hotelId);

    public DataAnalysisVo getStarDistribution(Long hotelId);
}
