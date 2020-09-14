package com.xixiwan.platform.config;

import com.xixiwan.platform.module.web.interceptor.GateLogInterceptor;
import com.xixiwan.platform.enums.PublicServletPathEnum;
import com.xixiwan.platform.interceptor.LockScreenInterceptor;
import com.xixiwan.platform.interceptor.LogOperationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public LockScreenInterceptor lockScreenInterceptor() {
        return new LockScreenInterceptor();
    }

    @Bean
    public LogOperationInterceptor logOperationInterceptor() {
        return new LogOperationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = PublicServletPathEnum.isList();
        registry.addInterceptor(new GateLogInterceptor()).excludePathPatterns(list);
        list.add("/login");
        list.add("/unlockScreen");
        registry.addInterceptor(lockScreenInterceptor()).excludePathPatterns(list);
        list.add("/");
        list.add("/**/list");
        list.add("/**/select*");
        list.add("/list");
        list.add("/forward");
        list.add("/dashboard");
        registry.addInterceptor(logOperationInterceptor()).excludePathPatterns(list);
    }

}
