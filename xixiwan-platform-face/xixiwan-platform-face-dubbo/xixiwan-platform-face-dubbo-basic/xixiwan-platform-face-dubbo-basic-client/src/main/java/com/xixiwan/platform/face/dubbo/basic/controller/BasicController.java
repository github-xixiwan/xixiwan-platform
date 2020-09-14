package com.xixiwan.platform.face.dubbo.basic.controller;

import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.face.dubbo.basic.api.BasicApi;
import com.xixiwan.platform.face.dubbo.basic.base.BaseMvcController;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BasicController extends BaseMvcController {

    @Reference
    private BasicApi basicApi;

    @GetMapping("/hello")
    public RestResponse<String> hello(@RequestParam("username") String username) {
        return RestResponse.success("hello basic " + basicApi.test(username));
    }

}
