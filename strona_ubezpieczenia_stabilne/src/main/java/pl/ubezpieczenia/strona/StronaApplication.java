package pl.ubezpieczenia.strona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Collections;

@EnableAsync
@SpringBootApplication
public class StronaApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(StronaApplication.class);
        springApplication.setDefaultProperties(Collections.singletonMap("server.port", "9999"));
        springApplication.run(args);
    }
}
