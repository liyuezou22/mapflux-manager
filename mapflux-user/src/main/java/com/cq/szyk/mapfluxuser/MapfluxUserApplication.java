package com.cq.szyk.mapfluxuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MapfluxUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapfluxUserApplication.class, args);
    }

}
