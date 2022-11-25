package sustech.hotel.order.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sustech.hotel.exception.BaseException;
import sustech.hotel.model.to.order.OrderTo;
import sustech.hotel.model.vo.order.OrderConfirmRespVo;
import sustech.hotel.model.vo.order.OrderConfirmVo;
import sustech.hotel.model.vo.order.PlaceOrderVo;
import sustech.hotel.order.entity.OrderEntity;
import sustech.hotel.order.service.OrderService;
import sustech.hotel.common.utils.Constant;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;


@RestController
@RequestMapping("order/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderService.queryPage(params);
        return new JsonResult<>(page);
    }


    /**
     * 查找数据表中的一条数据（根据主键查找）
     */
    @RequestMapping("/info/{orderId}")
    public JsonResult<OrderEntity> info(@PathVariable("orderId") String orderId) {
        OrderEntity order = orderService.getById(orderId);
        return new JsonResult<>(order);
    }

    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody OrderEntity order) {
        orderService.save(order);
        return new JsonResult<>();
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody OrderEntity order) {
        orderService.updateById(order);
        return new JsonResult<>();
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody String[] orderIds) {
        orderService.removeByIds(Arrays.asList(orderIds));
        return new JsonResult<>();
    }

    @RequestMapping("/queryByUser")
    public JsonResult<List<OrderTo>> queryByUser(Long userId) {
        return new JsonResult<>(orderService.queryOrderByUser(userId));
    }

    @PostMapping("/comfirmOrder")
    public JsonResult<OrderConfirmRespVo> confirmOrder(@RequestBody OrderConfirmVo orderConfirmVo) {
        OrderConfirmRespVo resp = new OrderConfirmRespVo();
        if (orderConfirmVo.getRoomId() != null)
            resp = orderService.confirmOrder(orderConfirmVo);
        else {
            // TODO: 2022/11/24  Randomly assign a room.
        }
        return new JsonResult<>(resp);
    }

    @RequestMapping("/generateOrder")
    public JsonResult<Void> generateOrder(@RequestBody PlaceOrderVo request) {
        try {
            String token = request.getToken();
            Long userid = orderService.checkUserID(token);
            OrderEntity order = new OrderEntity();
            order.setUserId(userid);
            order.setStartDate(request.getStartDate());
            order.setEndDate(request.getEndDate());
            order.setRoomId(request.getRoomID());
            orderService.placeOrder(order);
            return new JsonResult<>();
        } catch (BaseException e) {
            return new JsonResult<>(e);
        }
    }


}