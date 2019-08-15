package com.cq.szyk.mapfluxbasicdata.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


import static com.cq.szyk.mapfluxcommon.utils.FileUtils.getPublicKey;

@Configuration
@EnableResourceServer
//用于激活方法上的PreAuthorize注解
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String PUBLIC_KEY = "publickey.txt";
    @Autowired
    private AuthExceptionEntryPoint authExceptionEntryPoint;

    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    //定义JJwtAccessTokenConverter，使用jwt令牌
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(getPubKey());
        return converter;
    }

    /**
     * 获取非对称加密公钥 Key
     *
     * @return 公钥 Key
     */
    private String getPubKey() {
        Resource resource = new ClassPathResource(PUBLIC_KEY);
        return getPublicKey(resource);
    }

    //Http安全配置，对每个到达系统的http请求链接进行校验
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //所有请求必须认证通过
        http.authorizeRequests()
                //下边的路径放行
                .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
                        "/swagger-resources", "/swagger-resources/configuration/security",
                        "/swagger-ui.html", "/webjars/**", "/course/coursepic/list/**", "/course/courseview/**").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore(jwtAccessTokenConverter()))
                .authenticationEntryPoint(authExceptionEntryPoint);//配置token错误时，自定义返回结果
    }
}
