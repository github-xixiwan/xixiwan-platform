package com.xixiwan.platform.face.feign.order.config;

import com.xixiwan.platform.module.web.interceptor.GateLogInterceptor;
import com.xixiwan.platform.face.feign.order.enums.PublicServletPathEnum;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import io.shardingsphere.core.keygen.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GateLogInterceptor()).excludePathPatterns(PublicServletPathEnum.isList());
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }

}
