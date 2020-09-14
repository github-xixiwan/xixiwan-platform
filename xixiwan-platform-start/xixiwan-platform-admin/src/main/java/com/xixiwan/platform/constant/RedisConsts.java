package com.xixiwan.platform.constant;

public interface RedisConsts {

    /**
     * 缓存
     */
    String CACHE_NAME = "xixiwan-platform:admin";

    /**
     * 缓存KEY前缀
     */
    String CACHE_PREFIX = "targetClass + ':' + methodName + ':' +";

    /**
     * 缓存KEY前缀
     */
    String CACHE_KEY = "targetClass + ':' + methodName";

    /**
     * 签名校验随机数前缀
     */
    String NONCE_PREFIX_KEY = ":nonce:";

    /**
     * 登录用户前缀
     */
    String SINGLELOGIN_PREFIX_KEY = ":singleLogin:";

}
