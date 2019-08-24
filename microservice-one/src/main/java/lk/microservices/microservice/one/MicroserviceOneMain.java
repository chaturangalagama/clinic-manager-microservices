package lk.microservices.microservice.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceOneMain {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceOneMain.class, args);
    }
}
