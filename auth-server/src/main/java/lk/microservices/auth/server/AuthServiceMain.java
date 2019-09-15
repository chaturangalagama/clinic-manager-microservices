package lk.microservices.auth.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceMain {

        private static final Logger LOG = LoggerFactory.getLogger(AuthServiceMain.class);

        public static void main(String[] args) {
            SpringApplication.run(AuthServiceMain.class, args);
        }

    }

