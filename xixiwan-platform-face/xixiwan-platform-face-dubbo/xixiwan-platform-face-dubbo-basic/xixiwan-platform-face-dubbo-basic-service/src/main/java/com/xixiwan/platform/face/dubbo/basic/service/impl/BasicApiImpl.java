package com.xixiwan.platform.face.dubbo.basic.service.impl;

import com.xixiwan.platform.face.dubbo.basic.api.BasicApi;
import org.apache.dubbo.config.annotation.Service;

@Service
public class BasicApiImpl implements BasicApi {

    @Override
    public String test(String str) {
        System.out.println("111111111111");
        return "test：" + str;
    }

}