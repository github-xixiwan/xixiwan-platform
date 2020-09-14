package com.xixiwan.platform.module.common.util;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.security.MessageDigest;

/**
 * @ClassName: MD5Util
 * @Description: MD5工具类
 * @Author: Sente
 * @Date: 2018年10月2日 下午1:12:21
 * @Copyright: 2018 www.sto.cn Inc. All rights reserved.
 */
public class Md5Utils {

    public static final String CHARSET = "UTF-8";

    private Md5Utils() {
        throw new IllegalStateException("MD5Util class");
    }

    public static String encode(String src) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] bs = digest.digest(src.getBytes(CHARSET));
            String hexString = "";
            for (byte b : bs) {
                int temp = b & 255;
                if (temp < 16 && temp >= 0) {
                    hexString = hexString + "0" + Integer.toHexString(temp);
                } else {
                    hexString = hexString + Integer.toHexString(temp);
                }
            }
            return hexString;
        } catch (Exception e) {
            return ExceptionUtils.rethrow(e);
        }
    }

    public static String encode(byte[] src) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] bs = digest.digest(src);
            String hexString = "";
            for (byte b : bs) {
                int temp = b & 255;
                if (temp < 16 && temp >= 0) {
                    hexString = hexString + "0" + Integer.toHexString(temp);
                } else {
                    hexString = hexString + Integer.toHexString(temp);
                }
            }
            return hexString;
        } catch (Exception e) {
            return ExceptionUtils.rethrow(e);
        }
    }

}