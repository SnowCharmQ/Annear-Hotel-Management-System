package sustech.hotel.order.controller;

import com.alipay.api.AlipayApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.exception.BaseException;
import sustech.hotel.model.vo.order.PayVo;
import sustech.hotel.order.config.AlipayTemplate;
import sustech.hotel.order.service.OrderService;

@Controller
public class PabWebController {
    @Autowired
    private AlipayTemplate alipayTemplate;

    @Autowired
    private OrderService orderService;

//    @ResponseBody
//    @GetMapping(value = "/payOrder", produces = "text/html")
//    public String payOrder(@RequestParam("orderId") String orderId) throws AlipayApiException {
//        PayVo orderPay = orderService.getOrderPay(orderId);
//        return alipayTemplate.pay(orderPay);
//    }

    @ResponseBody
    @GetMapping("/payOrder")
    public JsonResult<String> payOrder(@RequestParam("orderId") String orderId) throws AlipayApiException {
        try {
            PayVo orderPay = orderService.getOrderPay(orderId);
            return new JsonResult<>(alipayTemplate.pay(orderPay));
        } catch (BaseException e) {
            return new JsonResult<>(e);
        }
    }
}
