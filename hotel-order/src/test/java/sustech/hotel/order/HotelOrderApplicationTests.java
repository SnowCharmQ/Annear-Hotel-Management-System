package sustech.hotel.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.order.OrderTo;
import sustech.hotel.order.controller.OrderController;
import sustech.hotel.order.entity.OrderOperationEntity;
import sustech.hotel.order.service.OrderOperationService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
class HotelOrderApplicationTests {
//    @Autowired
//    OrderController orderController;
//
//    @Autowired
//    OrderOperationService orderOperationService;
//
//    @Test
//    void contextLoads() {
//        System.out.println(orderController.queryByUser((long)12012705).getData());
//    }
//
//    @Test
//    void testInsert(){
//        OrderOperationEntity orderOperationEntity = new OrderOperationEntity();
//        orderOperationEntity.setOrderId("test1");
//        orderOperationEntity.setOperationTime(new Timestamp(System.currentTimeMillis()));
//        orderOperationEntity.setOperation(1);
//        System.out.println(orderOperationEntity.getOperationTime());
//        orderOperationService.save(orderOperationEntity);
//    }

}
