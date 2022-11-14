package sustech.hotel.thirdparty.component;

import lombok.Data;
import org.apache.http.HttpResponse;
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
        String host = "https://gyytz.market.alicloudapi.com";
        String path = "/sms/smsSend";
        String appcode = "ecfd6c0f2add43e59d9cec4e12a063e9";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>();
        querys.put("mobile", phone);
        querys.put("param", "**code**:" + code + ",**minute**:3");
        querys.put("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2");
        querys.put("templateId", "908e94ccf08b4476ba6c876d13f084ad");
        Map<String, String> bodies = new HashMap<>();
        HttpUtils.doPost(host, path, headers, querys, bodies);
    }
}
