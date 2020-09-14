package com.xixiwan.platform.face.feign.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = { "com.xixiwan.platform" })
@SpringBootApplication(scanBasePackages = { "com.xixiwan.platform" })
public class FeignOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignOrderServiceApplication.class, args);
	}

}
