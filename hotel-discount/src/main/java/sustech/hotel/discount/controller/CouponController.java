package sustech.hotel.discount.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sustech.hotel.discount.entity.CouponEntity;
import sustech.hotel.discount.service.CouponService;
import sustech.hotel.common.utils.Constant;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;


@RestController
@RequestMapping("discount/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = couponService.queryPage(params);
        return new JsonResult<>(Constant.OK, page);
    }


    /**
     * 查找数据表中的一条数据（根据主键查找）
     */
    @RequestMapping("/info/{couponId}")
    public JsonResult<CouponEntity> info(@PathVariable("couponId") Integer couponId){
		CouponEntity coupon = couponService.getById(couponId);
        return new JsonResult<>(Constant.OK, coupon);
    }

    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody CouponEntity coupon){
		couponService.save(coupon);
        return new JsonResult<>(Constant.OK);
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody CouponEntity coupon){
		couponService.updateById(coupon);
        return new JsonResult<>(Constant.OK);
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody Integer[] couponIds){
		couponService.removeByIds(Arrays.asList(couponIds));
        return new JsonResult<>(Constant.OK);
    }
}