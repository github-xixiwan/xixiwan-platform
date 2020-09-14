package com.xixiwan.platform.face.feign.order.service.impl;

import com.xixiwan.platform.face.feign.order.service.CommonService;
import io.shardingsphere.core.keygen.KeyGenerator;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {

    @Resource
    private KeyGenerator keyGenerator;

    @Override
    public Long generateKey() {
        Number key = keyGenerator.generateKey();
        return Long.parseLong(String.valueOf(key) + RandomUtils.nextInt(0, 10));
    }

    @Override
    public Long generateOrderKey() {
        RandomUtils.nextLong();
        return Long.parseLong(String.valueOf(System.currentTimeMillis()) + RandomUtils.nextInt(10000, 100000));
    }
}
