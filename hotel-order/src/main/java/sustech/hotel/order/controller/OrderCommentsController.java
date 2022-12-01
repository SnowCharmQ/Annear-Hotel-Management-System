package sustech.hotel.order.controller;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.order.OrderNotExistException;
import sustech.hotel.exception.order.OrderNotFinishException;
import sustech.hotel.model.vo.order.CommentVo;
import sustech.hotel.order.dao.BookingDao;
import sustech.hotel.order.dao.OrderCommentsDao;
import sustech.hotel.order.dao.OrderDao;
import sustech.hotel.order.entity.OrderCommentsEntity;
import sustech.hotel.order.entity.OrderEntity;
import sustech.hotel.order.entity.OrderOperationEntity;
import sustech.hotel.order.feign.RoomFeignService;
import sustech.hotel.order.service.BookingService;
import sustech.hotel.order.service.OrderCommentsService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.order.service.OrderOperationService;
import sustech.hotel.order.service.OrderService;


@RestController
@RequestMapping("order/ordercomments")
public class OrderCommentsController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderCommentsService orderCommentsService;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderOperationService orderOperationService;

    @Autowired
    private BookingDao bookingDao;

    @RequestMapping("/commentOrder")
    public JsonResult<Void> commentOrder(@RequestBody CommentVo comment) {
        OrderEntity orderEntity = orderService.getById(comment.getOrderId());
        if (orderEntity == null)
            throw new OrderNotExistException(ExceptionCodeEnum.ORDER_NOT_EXIST_EXCEPTION);
        if (orderEntity.getOrderStatus() != 3)
            throw new OrderNotFinishException(ExceptionCodeEnum.ORDER_NOT_FINISH_EXCEPTION);
        OrderCommentsEntity orderCommentsEntity = new OrderCommentsEntity();
        BeanUtils.copyProperties(comment, orderCommentsEntity);
        orderCommentsService.save(orderCommentsEntity);
        orderDao.updateOrderStatus(comment.getOrderId(), 4);
        OrderOperationEntity orderOperationEntity = new OrderOperationEntity();
        orderOperationEntity.setOperationTime(new Timestamp(System.currentTimeMillis()));
        orderOperationEntity.setOrderId(comment.getOrderId());
        orderOperationEntity.setOperation(4);
        orderOperationService.save(orderOperationEntity);
        bookingDao.deleteByOrderId(comment.getOrderId());
        return new JsonResult<>();
    }

    @ResponseBody
    @RequestMapping("/getComments")
    public JsonResult<PageUtils> getComments(@RequestParam Map<String, Object> params) {
        Object o = params.get("typeId");
        Long typeId = null;
        if (o != null) {
            typeId = Long.parseLong((String) o);
        }
        PageUtils pageUtils = orderCommentsService.getComments(typeId, params);
        return new JsonResult<>(pageUtils);
    }


    @ResponseBody
    @RequestMapping("/getAllComments")
    public JsonResult<String> getAllComments() {
        List<OrderCommentsEntity> entities = orderCommentsService.list();
        List<CommentVo> vos = entities.stream().map(o -> {
            CommentVo vo = new CommentVo();
            OrderEntity byId = orderService.getById(o.getOrderId());
            vo.setTypeId(byId.getTypeId());
            BeanUtils.copyProperties(o, vo);
            return vo;
        }).toList();
        return new JsonResult<>(JSON.toJSONString(vos));
    }

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderCommentsService.queryPage(params);
        return new JsonResult<>(page);
    }

    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody OrderCommentsEntity orderComments) {
        orderCommentsService.save(orderComments);
        return new JsonResult<>();
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody OrderCommentsEntity orderComments) {
        orderCommentsService.updateById(orderComments);
        return new JsonResult<>();
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody String[] orderIds) {
        orderCommentsService.removeByIds(Arrays.asList(orderIds));
        return new JsonResult<>();
    }
}