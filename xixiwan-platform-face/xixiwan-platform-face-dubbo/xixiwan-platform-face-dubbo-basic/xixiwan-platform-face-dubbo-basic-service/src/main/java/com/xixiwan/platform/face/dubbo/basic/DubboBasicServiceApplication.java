package com.xixiwan.platform.face.dubbo.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.xixiwan.platform"})
public class DubboBasicServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboBasicServiceApplication.class, args);
    }

}
