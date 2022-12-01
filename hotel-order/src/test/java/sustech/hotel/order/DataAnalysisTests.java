package sustech.hotel.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sustech.hotel.model.vo.order.DataAnalysisVo;
import sustech.hotel.order.controller.DataAnalysisController;
import sustech.hotel.order.controller.OrderController;
import sustech.hotel.order.entity.OrderOperationEntity;
import sustech.hotel.order.service.DataAnalysisService;
import sustech.hotel.order.service.OrderOperationService;

import java.sql.Timestamp;

@SpringBootTest
public class DataAnalysisTests {
//    @Autowired
//    DataAnalysisService dataAnalysisService;
//
//    @Test
//    void test(){
//        long hotelId = 1;
//
//        DataAnalysisVo vo = dataAnalysisService.getLastMonthOrderNumber(hotelId);
//
//        DataAnalysisVo vo1 = dataAnalysisService.getLastYearRoomTypeOrderNumber(hotelId);
//
//        DataAnalysisVo vo2 = dataAnalysisService.getLastYearIncome(hotelId);
//
//        DataAnalysisVo vo3 = dataAnalysisService.getStarDistribution(hotelId);
//    }
}
