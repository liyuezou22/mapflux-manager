package com.cq.szyk.mapfluxuser;

import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.cq.szyk.mapfluxuser.mapper")
@EntityScan(basePackages = {"com.cq.szyk.mapfluxmodel.users"})
@ComponentScan(basePackages = {"com.cq.szyk.mapfluxapi"})
@ComponentScan(basePackages = {"com.cq.szyk.mapfluxcommon"})
public class MapfluxUserApplication {
    private static final Logger LOG = LoggerFactory.getLogger(LoginConfig.class);
    public static void main(String[] args) {
        SpringApplication.run(MapfluxUserApplication.class, args);
        LOG.info("======map flux-user加载成功======");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
