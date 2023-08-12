package com.room18.calnetworth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EntityScan(basePackages = "com.room18.common.entity")
public class CalNetWorthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalNetWorthApplication.class, args);
    }

}
