package org.example.yolofarmbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YoloFarmBeApplication {
    public static void main(String[] args) {
        SpringApplication.run(YoloFarmBeApplication.class, args);
    }
}
