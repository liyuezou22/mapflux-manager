package com.cq.szyk.mapfluxgetway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MapfluxGetwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapfluxGetwayApplication.class, args);
    }

}
