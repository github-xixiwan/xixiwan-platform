package com.xixiwan.platform.face.feign.order.api;

import com.xixiwan.platform.module.common.rest.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@FeignClient("face-order")
public interface OrderApi {

    @GetMapping("/hello")
    @ResponseBody
    public RestResponse<String> hello(@RequestParam("username") String username);

}
