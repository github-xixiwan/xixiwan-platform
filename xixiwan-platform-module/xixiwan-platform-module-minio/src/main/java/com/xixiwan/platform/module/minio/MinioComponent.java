package com.xixiwan.platform.module.minio;

import java.io.InputStream;

public interface MinioComponent {

    void upload(String objectName, InputStream stream);

    void uploadBase64(String objectName, String base64);

    String getUrl(String objectName);

}
