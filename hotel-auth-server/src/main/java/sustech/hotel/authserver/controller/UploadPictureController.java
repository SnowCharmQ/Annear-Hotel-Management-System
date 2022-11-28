package sustech.hotel.authserver.controller;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sustech.hotel.common.utils.JsonResult;
import java.io.*;

@Controller
public class UploadPictureController {
    @ResponseBody
    @RequestMapping("/uploadPicture")
    public JsonResult<String> uploadPicture(@RequestParam("file") MultipartFile file, @RequestParam("orderId") String orderId) throws IOException {
        System.out.println(file.getSize());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(orderId);
        File f = null;
        InputStream ins = file.getInputStream();
        f = new File(file.getOriginalFilename());
        inputStreamToFile(ins, f);
        String secretId = "AKIDyAKJJI5nJcs298wZyDMN9jkhS0ttkbIC";
        String secretKey = "1sZDnkVH2gAWjBg5VwGPSQFsHUVRND4q";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region("ap-guangzhou");
        ClientConfig config = new ClientConfig(region);
        config.setHttpProtocol(HttpProtocol.https);
        COSClient client = new COSClient(cred, config);
        String bucketName = "ooad-1312953997";
        String key = orderId + "/img/" + f.getName();
        PutObjectRequest request = new PutObjectRequest(bucketName, key, f);
        PutObjectResult result = client.putObject(request);
        System.out.println(client.getObjectUrl(bucketName, key));//输出的是图片对应的URL
        return new JsonResult<String>(client.getObjectUrl(bucketName, key).toString());
    }

    @ResponseBody
    @RequestMapping("/uploadVideo")
    public JsonResult<String> uploadVideo(@RequestParam("file") MultipartFile file, @RequestParam("orderId") String orderId) throws IOException {
        System.out.println(file.getSize());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(orderId);
        File f = null;
        InputStream ins = file.getInputStream();
        f = new File(file.getOriginalFilename());
        inputStreamToFile(ins, f);
        String secretId = "AKIDyAKJJI5nJcs298wZyDMN9jkhS0ttkbIC";
        String secretKey = "1sZDnkVH2gAWjBg5VwGPSQFsHUVRND4q";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region("ap-guangzhou");
        ClientConfig config = new ClientConfig(region);
        config.setHttpProtocol(HttpProtocol.https);
        COSClient client = new COSClient(cred, config);
        String bucketName = "ooad-1312953997";
        String key = orderId + "/video/" + f.getName();
        PutObjectRequest request = new PutObjectRequest(bucketName, key, f);
        PutObjectResult result = client.putObject(request);
        System.out.println(client.getObjectUrl(bucketName, key));//输出的是图片对应的URL
        return new JsonResult<String>(client.getObjectUrl(bucketName, key).toString());
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
