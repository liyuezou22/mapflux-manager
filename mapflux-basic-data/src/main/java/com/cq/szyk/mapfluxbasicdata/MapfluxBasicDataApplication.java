package com.cq.szyk.mapfluxbasicdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.cq.szyk.mapfluxbasicdata.mapper")
@EntityScan(basePackages = {"com.cq.szyk.mapfluxmodel.basicdata"})
@ComponentScan(basePackages = {"com.cq.szyk.mapfluxapi"})
@ComponentScan(basePackages = {"com.cq.szyk.mapfluxcommon"})
public class MapfluxBasicDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapfluxBasicDataApplication.class, args);
    }

}
