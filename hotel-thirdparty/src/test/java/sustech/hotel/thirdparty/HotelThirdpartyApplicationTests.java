package sustech.hotel.thirdparty;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sustech.hotel.common.utils.HttpUtils;

import java.io.File;
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

    @Test
    void uploadPicture() {
        //文件绝对路径
        File file = new File("D:\\1A\\Java lab\\OOAD-Project\\hotel-thirdparty\\src\\test\\java\\sustech\\hotel\\thirdparty\\img\\Vue.png");
        //下面都不用改动
        String secretId = "AKIDyAKJJI5nJcs298wZyDMN9jkhS0ttkbIC";
        String secretKey = "1sZDnkVH2gAWjBg5VwGPSQFsHUVRND4q";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region("ap-guangzhou");
        ClientConfig config = new ClientConfig(region);
        config.setHttpProtocol(HttpProtocol.https);
        COSClient client = new COSClient(cred, config);
        String bucketName = "ooad-1312953997";
        String key = "img/" + file.getName();
        PutObjectRequest request = new PutObjectRequest(bucketName, key, file);
        PutObjectResult result = client.putObject(request);
        System.out.println(client.getObjectUrl(bucketName, key));//输出的是图片对应的URL
    }

}
