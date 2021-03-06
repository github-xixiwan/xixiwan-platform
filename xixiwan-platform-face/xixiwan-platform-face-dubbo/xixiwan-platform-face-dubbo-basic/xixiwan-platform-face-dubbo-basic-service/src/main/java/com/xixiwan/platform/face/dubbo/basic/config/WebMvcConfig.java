package com.xixiwan.platform.face.dubbo.basic.config;

import com.xixiwan.platform.module.web.interceptor.GateLogInterceptor;
import com.xixiwan.platform.face.dubbo.basic.enums.PublicServletPathEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GateLogInterceptor()).excludePathPatterns(PublicServletPathEnum.isList());
    }

}
