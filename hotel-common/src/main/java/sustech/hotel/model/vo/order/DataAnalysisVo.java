package sustech.hotel.model.vo.order;

import kotlin.Pair;
import lombok.Data;

import java.util.List;

@Data
public class DataAnalysisVo {
    private List<String> xdata;
    private List<Integer> ydata;

    public DataAnalysisVo(List<String> xdata, List<Integer> ydata) {
        this.xdata = xdata;
        this.ydata = ydata;
    }
}
