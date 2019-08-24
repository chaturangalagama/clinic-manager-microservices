package lk.microservices.microservice.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceTwoMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MicroserviceTwoMain.class, args);
    }
}