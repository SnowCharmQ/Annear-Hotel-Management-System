package sustech.hotel.thirdparty.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import sustech.hotel.common.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "alicloud.sms")
public class SmsComponent {
    private String host;
    private String path;
    private String appcode;

    public void sendSmsCode(String phone, String code) throws Exception {
        //发送短信
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + appcode);
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> queries = new HashMap<>();
        String content = "【OOAD】您的验证码是" + code + "，1分钟内有效！";
        queries.put("content", content);
        queries.put("mobile", phone);
        Map<String, String> bodies = new HashMap<>();
        HttpUtils.doPost(host, path, headers, queries, bodies);
    }
}
