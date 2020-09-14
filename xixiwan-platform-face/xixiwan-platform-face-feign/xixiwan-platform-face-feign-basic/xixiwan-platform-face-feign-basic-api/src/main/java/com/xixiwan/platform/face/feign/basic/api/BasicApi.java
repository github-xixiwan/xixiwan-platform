package com.xixiwan.platform.face.feign.basic.api;

import com.xixiwan.platform.module.common.rest.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "face-basic")
public interface BasicApi {

    @GetMapping("/hello")
    @ResponseBody
    public RestResponse<String> hello(@RequestParam("username") String username);

}
