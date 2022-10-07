package sustech.hotel.discount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@EnableDiscoveryClient
@SpringBootApplication
public class HotelDiscountApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelDiscountApplication.class, args);
    }

}
