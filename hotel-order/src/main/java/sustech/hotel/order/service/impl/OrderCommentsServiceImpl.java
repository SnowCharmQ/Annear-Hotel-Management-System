package sustech.hotel.order.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.Constant;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.model.to.hotel.CommentInfoTo;
import sustech.hotel.model.vo.hotel.CommentShowVo;
import sustech.hotel.order.dao.OrderCommentsDao;
import sustech.hotel.order.entity.OrderCommentsEntity;
import sustech.hotel.order.entity.OrderEntity;
import sustech.hotel.order.feign.RoomFeignService;
import sustech.hotel.order.service.OrderCommentsService;
import sustech.hotel.order.service.OrderService;


@Service("orderCommentsService")
public class OrderCommentsServiceImpl extends ServiceImpl<OrderCommentsDao, OrderCommentsEntity> implements OrderCommentsService {

    @Autowired
    private RoomFeignService roomFeignService;

    @Autowired
    private OrderService orderService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderCommentsEntity> page = this.page(
                new Query<OrderCommentsEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils getComments(Long typeId, Map<String, Object> params) {
        List<OrderCommentsEntity> entities = this.list();
        List<CommentShowVo> vos = entities.stream().map(o -> {
            CommentShowVo vo = new CommentShowVo();
            OrderEntity byId = orderService.getById(o.getOrderId());
            vo.setTypeId(byId.getTypeId());
            vo.setScore(byId.getScore());
            BeanUtils.copyProperties(o, vo);
            return vo;
        }).toList();
        if (typeId != null) {
            vos = vos.stream().filter(o -> Objects.equals(typeId, o.getTypeId())).toList();
        }
        List<Long> typeIds = new ArrayList<>();
        if (typeId == null || typeId == 0) {
            typeIds = vos.stream().map(CommentShowVo::getTypeId).toList();
        } else {
            typeIds.add(typeId);
        }
        List<CommentInfoTo> data = roomFeignService.getCommentInfo(typeIds).getData();
        Map<Long, CommentInfoTo> map = data.stream().collect(Collectors.toMap(CommentInfoTo::getTypeId, o -> o));
        for (CommentShowVo vo : vos) {
            CommentInfoTo info = map.get(vo.getTypeId());
            vo.setTypeName(info.getTypeName());
            vo.setHotelName(info.getHotelName());
        }
        int curPage = 1;
        int limit = 10;
        if (params.get(Constant.PAGE) != null) {
            curPage = Integer.parseInt(params.get(Constant.PAGE).toString());
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Integer.parseInt(params.get(Constant.LIMIT).toString());
        }
        return new PageUtils(vos, vos.size(), limit, curPage);
    }

}