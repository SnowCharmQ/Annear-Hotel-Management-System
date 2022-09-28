package sustech.hotel.discount.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sustech.hotel.discount.entity.SeckillEntity;
import sustech.hotel.discount.service.SeckillService;
import sustech.hotel.common.utils.Constant;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;


@RestController
@RequestMapping("discount/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillService.queryPage(params);
        return new JsonResult<>(Constant.OK, page);
    }

    /**
     * 查找数据表中的一条数据（根据主键查找）
     */
    @RequestMapping("/info/{seckillId}")
    public JsonResult<SeckillEntity> info(@PathVariable("seckillId") Integer seckillId){
		SeckillEntity seckill = seckillService.getById(seckillId);
        return new JsonResult<>(Constant.OK, seckill);
    }

    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody SeckillEntity seckill){
		seckillService.save(seckill);
        return new JsonResult<>(Constant.OK);
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody SeckillEntity seckill){
		seckillService.updateById(seckill);
        return new JsonResult<>(Constant.OK);
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody Integer[] seckillIds){
		seckillService.removeByIds(Arrays.asList(seckillIds));
        return new JsonResult<>(Constant.OK);
    }
}