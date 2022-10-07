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

    public void sendSmsCode(String phone, String code) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + appcode);
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        bodys.put("content", "code:" + code);
        bodys.put("phone_number", phone);
        bodys.put("template_id", "TPL_0000");
        try {
            HttpUtils.doPost(host, path, headers, querys, bodys);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
