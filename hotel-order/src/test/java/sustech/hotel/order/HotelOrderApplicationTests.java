package sustech.hotel.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.order.OrderTo;
import sustech.hotel.order.controller.OrderController;

import java.util.List;

@SpringBootTest
class HotelOrderApplicationTests {
    @Autowired
    OrderController orderController;
    @Test
    void contextLoads() {
        System.out.println(orderController.queryByUser((long)12012705).getData());
    }

}
