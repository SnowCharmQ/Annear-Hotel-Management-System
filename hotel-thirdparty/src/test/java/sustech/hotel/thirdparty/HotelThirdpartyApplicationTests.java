package sustech.hotel.thirdparty;

import org.apache.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sustech.hotel.common.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class HotelThirdpartyApplicationTests {

    @Test
    void test() {
        String host = "https://gyytz.market.alicloudapi.com";
        String path = "/sms/smsSend";
        String appcode = "ecfd6c0f2add43e59d9cec4e12a063e9";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", "22");
        querys.put("param", "**code**:000000,**minute**:3");
        querys.put("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2");
        querys.put("templateId", "908e94ccf08b4476ba6c876d13f084ad");
        Map<String, String> bodies = new HashMap<String, String>();
        try {
            HttpResponse response = HttpUtils.doPost(host, path, headers, querys, bodies);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
