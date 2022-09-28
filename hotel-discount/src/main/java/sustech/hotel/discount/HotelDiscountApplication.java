package sustech.hotel.discount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HotelDiscountApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelDiscountApplication.class, args);
    }

}
