package sustech.hotel.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.model.vo.order.DataAnalysisVo;
import sustech.hotel.order.entity.BookingEntity;
import sustech.hotel.order.entity.OrderEntity;

public interface DataAnalysisService extends IService<OrderEntity> {
    public DataAnalysisVo getLastMonthOrderNumber();

    public DataAnalysisVo getLastYearRoomTypeOrderNumber();

    public DataAnalysisVo getLastYearIncome();

    public DataAnalysisVo getStarDistribution();
}
