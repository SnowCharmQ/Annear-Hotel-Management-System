package sustech.hotel.room.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sustech.hotel.room.entity.LayoutEntity;
import sustech.hotel.room.service.LayoutService;
import sustech.hotel.common.utils.Constant;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;


@RestController
@RequestMapping("room/layout")
public class LayoutController {

    @Autowired
    private LayoutService layoutService;

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = layoutService.queryPage(params);
        return new JsonResult<>(Constant.OK, page);
    }


    /**
     * 查找数据表中的一条数据（根据主键查找）
     */
    @RequestMapping("/info/{layoutId}")
    public JsonResult<LayoutEntity> info(@PathVariable("layoutId") Integer layoutId){
		LayoutEntity layout = layoutService.getById(layoutId);
        return new JsonResult<>(Constant.OK, layout);
    }

    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody LayoutEntity layout){
		layoutService.save(layout);
        return new JsonResult<>(Constant.OK);
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody LayoutEntity layout){
		layoutService.updateById(layout);
        return new JsonResult<>(Constant.OK);
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody Integer[] layoutIds){
		layoutService.removeByIds(Arrays.asList(layoutIds));
        return new JsonResult<>(Constant.OK);
    }
}