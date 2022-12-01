package sustech.hotel.order.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import sustech.hotel.model.vo.order.PayVo;

@Component
@Data
public class AlipayTemplate {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public String app_id = "2021000121697020";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCGdVNP4SB4LcahTjgbCZ6qT30ZQTY3DQezpCKHAg2deqO4hVWZ+cq5l7anzCBHLzUjTOYMxfPOoOqfMo6xuX2RZqzeIDIAODKz/aegKOwcENjV8EAllSCmIAptKBCdjcdebDSPnH13Ztz4XwTBZ9mEQ2yMxaFYm3Ccg8LMEN9wswNkdPLLgBZsYvGNFqRULxci2+dKwgtoL2slxec1j0igEpU6pCsiwFsIA0zvB/TDIEjMvsRFft6fQQCw4xd8d8BWSIPDfpsLdneJOw+RYX++Q5gnX+Z7OVCXKbMr4t9KH5noWa4Qp92uWuZm/dh8V5DZZkfR9JZc5N5qfmBl+S+pAgMBAAECggEBAIS+xFeyjVnY56dND/sxjiHahEkwppPHG3lxbF0Z29NDxutfR9nSUSDV9ZDH6H4ezzlgrCakMEYpfDlbsqEn3h503eoWowawdO5aWOm4PRKgV1Mv9ESmbCtWqrSFv5Gs7c38yg+WkhAVz8auGIXRP2BjKIH6g4Z1P8JmGyVY6x/ZTcUunbZE+CdtadYaKLNxQJWWMWUt/38UJYxfmf6XzM+9DsJtXq0LD6Eek7jvtPqY8ARU6amUZmcNWqVNeKh1UthSVIEjtM8WN7N6xKHHuwQYUVPy6of7BABQkJnykzfuHglm5oB5/M7Y9K3vPnlV4PX0q09WWHxleYhzUNw5pAECgYEAwG1FUvC1F0iAJZoP/AqYPmkUkeXDjmXXGCC1/S814nWvrjtoWoqpsokWKzwT+rd34Y7VI786ZvBg3M/cX1X2XpqBqtX+2mefkBAqq0l7iaEbxmydao80VIciQhyLBbovp2GeVmRdsU3ruiKJAfa20SGDBt6hc2MUTlvZ39lqeOkCgYEAsuFL7SnDlpTbRCccWCLewPWDKE68laF7Hbz13s+fUIhfOIFJX6nEhDiRtwn2ALsiJ0Q5QfOs1bW+w3I1lNuZXpPZmTBhbeeCVSq6uROZN0uuDZ2XNnjeTAtDWU91jSiFtHDzcox4BHGBBOE/twyD1TgZT8uuVD2RVB92czWOyMECgYBglbhJJDbRQ/vGY+kICk1Wbin7/UbF78oh+zE9rvet/oI6wujjIVyCF5sAAnzIyFQs7COBeNeYmakUzGSBmcU6wPS6ygTMnVyS0/qG1TBj6XDmjNgKh2ytzNJeOrNxIX4P1sjB9kis2T6rw+/fA3XJzLdWuGJzZ1Tm6+WVHSz7GQKBgDzStopxxPuSqevGkLC3Sp653Fr8SstmGQLHmaKlXjp35EjGTTil9RgwU/u0+1zMRJStcfqm7IHORosWbnmrIb8y7q5T8Qz5xytGWC9OJq7SoOAoUZ5IAu5AdioMBqB0wuHT9jNg5/46ye/q10taigkaBiFH1IL4XE4KVLBf6akBAoGAH31rztvFq6bQx0RtkTxCTC7ICIDcGq0tdVWhhKDUG2Csddh8F8MkQ9Wwf4dHuEX13dZ/iRPioXG6Ha9OYfcSDqJmFyUn23lapdLat7mOQqDzqRDRl6v1tLBCpTrF36z9sLPQ5jgHiGcJIFNb12ehx4GGNKK/2Fodwcc6LUlEPt8=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiBrIrskblQd4H40XwHX/NBVJDlSvWFK0p+veYnLq4ssf71W90XqJQX4O5qyDeL5oNkTSdJHhlBB3nYOW2Ygz1jgsVxh/vNXsaQJ+IgYdQe1ze23cIcliUEvyehtI8AGcznwfPPCt4rGcV5jaOGVqndkOYdFOOCWUspbAXosPQyr9NybWJqRn5wKgHPzLKTGTBLTELyEN+XuW4/SjFHbOX7z013j5YwkhwkToN6VWaAtR4nRU7XhE9GADg+qIt2ZeRPyCljAwdDZdf9axBrtQj2bW1ponbzqGcgYJlh36cMBWWyh+FEyC9/Rg84f9Zk+Qi4kIuJLF38awsyvqp8sVTwIDAQAB";

    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    public String notify_url = "http://110.41.16.220:12345/order/payed/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    public String return_url = "http://110.41.16.220:8080/dist/#/orderList";

    // 签名方式
    private String sign_type = "RSA2";

    // 字符编码格式
    private String charset = "utf-8";

    //订单超时时间
    private String timeout = "10m";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    public String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应：" + result);

        return result;

    }
}