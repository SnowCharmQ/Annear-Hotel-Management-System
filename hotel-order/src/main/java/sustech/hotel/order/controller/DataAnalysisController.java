package sustech.hotel.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.vo.order.DataAnalysisVo;
import sustech.hotel.order.service.DataAnalysisService;


@RestController
@RequestMapping("order/analysis")
public class DataAnalysisController {
    @Autowired
    DataAnalysisService dataAnalysisService;

    @ResponseBody
    @GetMapping("/getLastMonthOrderNumber")
    public JsonResult<DataAnalysisVo> getLastMonthOrderNumber(Long hotelId) {
        DataAnalysisVo vo = dataAnalysisService.getLastMonthOrderNumber(hotelId);
        return new JsonResult<>(vo);
    }

    @ResponseBody
    @GetMapping("/getLastYearRoomTypeOrderNumber")
    public JsonResult<DataAnalysisVo> getLastYearRoomTypeOrderNumber(Long hotelId) {
        DataAnalysisVo vo = dataAnalysisService.getLastYearRoomTypeOrderNumber(hotelId);
        return new JsonResult<>(vo);
    }

    @ResponseBody
    @GetMapping("/getLastYearIncome")
    public JsonResult<DataAnalysisVo> getLastYearIncome(Long hotelId) {
        DataAnalysisVo vo = dataAnalysisService.getLastYearIncome(hotelId);
        return new JsonResult<>(vo);
    }

    @ResponseBody
    @GetMapping("/getStarDistribution")
    public JsonResult<DataAnalysisVo> getStarDistribution(Long hotelId) {
        DataAnalysisVo vo = dataAnalysisService.getStarDistribution(hotelId);
        return new JsonResult<>(vo);
    }
}
