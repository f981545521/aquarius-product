package cn.acyou.aquarius.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AquariusProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(AquariusProductApplication.class, args);
    }

}
