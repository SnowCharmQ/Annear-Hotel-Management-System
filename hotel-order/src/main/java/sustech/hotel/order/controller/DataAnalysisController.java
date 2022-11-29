package sustech.hotel.order.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.vo.member.PhoneVo;
import sustech.hotel.model.vo.order.DataAnalysisVo;
import sustech.hotel.order.entity.OrderEntity;
import sustech.hotel.order.service.DataAnalysisService;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("order/analysis")
public class DataAnalysisController {
    @Autowired
    DataAnalysisService dataAnalysisService;

    @GetMapping("/getLastMonthOrderNumber")
    public JsonResult<DataAnalysisVo> getLastMonthOrderNumber() {
        DataAnalysisVo vo = dataAnalysisService.getLastMonthOrderNumber();
        return new JsonResult<>(vo);
    }

    public static void main(String[] args) {
        DataAnalysisController analysisController = new DataAnalysisController();
        analysisController.getLastMonthOrderNumber();
    }
}
