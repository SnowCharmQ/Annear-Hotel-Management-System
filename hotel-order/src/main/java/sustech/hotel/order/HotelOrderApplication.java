package sustech.hotel.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;
import sustech.hotel.order.service.OrderOperationService;

@EnableOpenApi
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class HotelOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelOrderApplication.class, args);
    }

}
