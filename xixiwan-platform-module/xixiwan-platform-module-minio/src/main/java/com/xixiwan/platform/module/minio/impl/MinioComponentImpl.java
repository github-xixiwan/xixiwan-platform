package com.xixiwan.platform.module.minio.impl;

import com.xixiwan.platform.module.minio.MinioComponent;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component("minioComponent")
public class MinioComponentImpl implements MinioComponent {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(MinioComponentImpl.class);

    @Value("${minio.bucketName}")
    private String bucketName;

    @Resource
    private MinioClient minioClient;

    private void bucketExists() throws IOException, InvalidKeyException, InvalidResponseException,
            RegionConflictException, InsufficientDataException, NoSuchAlgorithmException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        if (!minioClient.bucketExists(bucketName)) {
            minioClient.makeBucket(bucketName);
        }
    }

    @Override
    public void upload(String objectName, InputStream stream) {
        try {
            bucketExists();
            PutObjectOptions options = new PutObjectOptions(stream.available(), -1);
            minioClient.putObject(bucketName, objectName, stream, options);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | XmlParserException | RegionConflictException e) {
            logger.error("minio_upload_exception", e);
        }
    }

    @Override
    public void uploadBase64(String objectName, String base64) {
        try {
            bucketExists();
            ByteArrayInputStream stream = new ByteArrayInputStream(Base64.getDecoder().decode(base64));
            PutObjectOptions options = new PutObjectOptions(stream.available(), -1);
            minioClient.putObject(bucketName, objectName, stream, options);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | XmlParserException | RegionConflictException e) {
            logger.error("minio_uploadBase64_exception", e);
        }
    }

    @Override
    public String getUrl(String objectName) {
        try {
            return minioClient.presignedGetObject(bucketName, objectName);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | XmlParserException | InvalidExpiresRangeException e) {
            logger.error("minio_getUrl_exception", e);
            return "";
        }
    }
}
