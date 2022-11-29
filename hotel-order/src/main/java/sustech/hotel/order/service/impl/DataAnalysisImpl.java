package sustech.hotel.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kotlin.Pair;
import org.springframework.stereotype.Service;
import sustech.hotel.model.vo.order.DataAnalysisVo;
import sustech.hotel.order.dao.OrderDao;
import sustech.hotel.order.entity.OrderEntity;
import sustech.hotel.order.service.DataAnalysisService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("dataAnalysisService")
public class DataAnalysisImpl extends ServiceImpl<OrderDao, OrderEntity> implements DataAnalysisService {
    public DataAnalysisVo getLastMonthOrderNumber() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -31);
        Date start = c.getTime();
        Date end = new Date();
        int[] orderCount = new int[32];
//        HashMap<Integer, Integer> orderCount = new HashMap<>();
//        for (int i = 0; i < 30; i++) {
//            int d = c.get(Calendar.DAY_OF_MONTH);
//            orderCount.put(d, 0);
//            c.add(Calendar.DATE, 1);
//        }
        List<OrderEntity> orders = this.baseMapper.selectList(new QueryWrapper<OrderEntity>().and(
                o -> o.ge("start_date", start).le("end_date", end)));

        Calendar temp = Calendar.getInstance();
        for (OrderEntity entity : orders) {
            Date date = entity.getStartDate();
            temp.setTime(date);
            int day = temp.get(Calendar.DATE);
            orderCount[day] += 1;
        }

        c = Calendar.getInstance();
        c.add(Calendar.DATE, -31);
        List<String> xdata = new ArrayList<>();
        List<Integer> ydata = new ArrayList<>();
        SimpleDateFormat ft = new SimpleDateFormat("MM-dd");
        for (int i = 0; i < 30; i++) {
            String day = ft.format(c.getTime());
            int count = orderCount[c.get(Calendar.DATE)];
            xdata.add(day);
            ydata.add(count);
            c.add(Calendar.DATE, 1);
        }

        return new DataAnalysisVo(xdata, ydata);
    }

    public DataAnalysisVo getLastYearRoomTypeOrderNumber() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -12);
        c.set(Calendar.DAY_OF_MONTH, 0);
        Date start = c.getTime();
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 0);
        Date end = c.getTime();
//        System.out.println(start);
//        System.out.println(end);
        HashMap<Long, Integer> orderCount = new HashMap<>();
        List<OrderEntity> orders = this.baseMapper.selectList(new QueryWrapper<OrderEntity>().and(
                o -> o.ge("start_date", start).le("end_date", end)));


        for (OrderEntity entity : orders) {
            long typeId = entity.getTypeId();
            if (orderCount.containsKey(typeId)) {
                orderCount.put(typeId, orderCount.get(typeId) + 1);
            } else {
                orderCount.put(typeId, 1);
            }
        }

        List<Pair<Long, Integer>> data = new ArrayList<>();
        for (Long key : orderCount.keySet()) {
            data.add(new Pair<>(key, orderCount.get(key)));
        }

        data.sort((o1, o2) -> {
            if (o1.component1() < o2.component1()) {
                return -1;
            } else if (o1.component1().equals(o2.component1())) return 0;
            else return 1;
        });

        List<String> xdata = new ArrayList<>();
        List<Integer> ydata = new ArrayList<>();

        for (Pair<Long, Integer> p : data) {
            xdata.add("Type " + p.component1());
            ydata.add(p.component2());
        }

        return new DataAnalysisVo(xdata, ydata);
    }

    public DataAnalysisVo getLastYearIncome() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -12);
        Date start = c.getTime();
        Date end = new Date();
        BigDecimal[] incomeCount = new BigDecimal[13];
        for (int i = 0; i < 13; i++) {
            incomeCount[i] = new BigDecimal(0);
        }
        List<OrderEntity> orders = this.baseMapper.selectList(new QueryWrapper<OrderEntity>().and(
                o -> o.ge("start_date", start).le("end_date", end)));

        Calendar temp = Calendar.getInstance();
        for (OrderEntity entity : orders) {
            Date date = entity.getStartDate();
            temp.setTime(date);
            int day = temp.get(Calendar.MONTH);
            incomeCount[day] = incomeCount[day].add(entity.getAfterDiscount());
        }

        c = Calendar.getInstance();
        c.add(Calendar.MONTH, -12);
        List<String> xdata = new ArrayList<>();
        List<Integer> ydata = new ArrayList<>();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
        for (int i = 0; i < 12; i++) {
            String day = ft.format(c.getTime());
            BigDecimal income = incomeCount[c.get(Calendar.MONTH)];
            xdata.add(day);
            ydata.add(income.intValue());
            c.add(Calendar.MONTH, 1);
        }

        return new DataAnalysisVo(xdata, ydata);
    }


    public DataAnalysisVo getStarDistribution() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -12);
        Date start = c.getTime();
        Date end = new Date();
        int[] score = new int[6];
        List<OrderEntity> orders = this.baseMapper.selectList(new QueryWrapper<OrderEntity>().and(
                o -> o.ge("start_date", start).le("end_date", end)));

        for (OrderEntity entity : orders) {
            score[entity.getScore()] += 1;
        }

        List<String> xdata = new ArrayList<>();
        List<Integer> ydata = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            xdata.add("Star " + i);
            ydata.add(score[i]);
        }

        return new DataAnalysisVo(xdata, ydata);
    }


    public static void main(String[] args) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -31);
        Date start = c.getTime();
        Date end = new Date();
        c.setTime(end);
        System.out.println(end);
        System.out.println(c.get(Calendar.DATE));
    }
}
