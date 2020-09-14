package com.xixiwan.platform.module.common.util;

import org.apache.commons.lang3.StringUtils;

import java.time.Instant;

public class TokenUtils {

    private static final String CHARSET = "UTF-8";

    private TokenUtils() {
        throw new IllegalStateException("TokenUtil class");
    }

    private static String parseFromUrl(String filePath) {
        String group = "";
        //先分隔开路径
        String[] paths = filePath.split("/");
        if (paths.length == 1) {
            return "";
        }
        for (String item : paths) {
            if (item.contains("group")) {
                group = item;
                break;
            }
        }
        // 获取group起始位置
        int pathStartPos = filePath.indexOf(group) + group.length() + 1;
        return filePath.substring(pathStartPos);
    }

    private static String getToken(String remoteFilename, int ts, String secretKey) {
        try {
            byte[] bsFilename = remoteFilename.getBytes(CHARSET);
            byte[] bsKey = secretKey.getBytes(CHARSET);
            byte[] bsTimestamp = String.valueOf(ts).getBytes(CHARSET);
            byte[] buff = new byte[bsFilename.length + bsKey.length + bsTimestamp.length];
            System.arraycopy(bsFilename, 0, buff, 0, bsFilename.length);
            System.arraycopy(bsKey, 0, buff, bsFilename.length, bsKey.length);
            System.arraycopy(bsTimestamp, 0, buff, bsFilename.length + bsKey.length, bsTimestamp.length);
            return Md5Utils.encode(buff);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getFileUrl(String fileUrl, String secretKey) {
        if (StringUtils.isNotBlank(fileUrl)) {
            String filePath = parseFromUrl(fileUrl);
            if (StringUtils.isNotBlank(filePath)) {
                int ts = (int) Instant.now().getEpochSecond();
                String token = getToken(filePath, ts, secretKey);
                StringBuilder sb = new StringBuilder();
                sb.append("?token=").append(token);
                sb.append("&ts=").append(ts);
                return fileUrl + sb.toString();
            }
        }
        return fileUrl;
    }

}
