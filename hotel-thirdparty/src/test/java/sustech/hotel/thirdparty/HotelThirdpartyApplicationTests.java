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
        String host = "https://cxkjsms.market.alicloudapi.com";
        String appcode = "ecfd6c0f2add43e59d9cec4e12a063e9";
        String code = "123456";
        String phone = "15305979533";
        String path = "/chuangxinsms/dxjk";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> queries = new HashMap<>();
        String content = "【创信】你的验证码是：" + code + "，1分钟内有效！";
        queries.put("content", content);
        queries.put("mobile", phone);
        Map<String, String> bodies = new HashMap<>();
        try {
            HttpResponse response = HttpUtils.doPost(host, path, headers, queries, bodies);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
