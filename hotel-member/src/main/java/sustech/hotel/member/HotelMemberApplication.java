package sustech.hotel.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HotelMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelMemberApplication.class, args);
    }

}
