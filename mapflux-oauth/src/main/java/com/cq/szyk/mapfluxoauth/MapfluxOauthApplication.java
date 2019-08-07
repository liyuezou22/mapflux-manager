package com.cq.szyk.mapfluxoauth;

import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.cq.szyk.mapfluxmodel.users"})
@MapperScan("com.cq.szyk.mapfluxoauth.mapper")
@ComponentScan(basePackages = "com.cq.szyk.mapfluxoauth.service")
@ComponentScan(basePackages = "com.cq.szyk.mapfluxoauth.config")
public class MapfluxOauthApplication {
    private static final Logger LOG = LoggerFactory.getLogger(LoginConfig.class);
    public static void main(String[] args) {
        SpringApplication.run(MapfluxOauthApplication.class, args);
        LOG.info("======oauth启动成功======");

    }

}
