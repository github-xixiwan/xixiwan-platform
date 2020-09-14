package com.xixiwan.platform.module.minio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MinioTest.App.class},
        properties = {
                "minio.url=http://minio.xixiwan.cloud",
                "minio.accessKey=minioadmin",
                "minio.secretKey=minioadmin",
                "minio.bucketName=xixiwan-platform",
        })
public class MinioTest {

    @Resource
    private MinioComponent minioComponent;

    @Test
    public void uploadBase64() {
        minioComponent.uploadBase64("test", "iVBORw0KGgoAAAANSUhEUgAAAEMAAAAMCAYAAAA5xADKAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAASJSURBVEhL7ZVrbFNlGMd/vZye3rv1Xlagg66bZRljOm4KYRKDEjUGjBAjl0SJ0RgvX0D8YIxiwidNjMEYkKgfDBH9gh8g8RIcBAmOjeAQcBfY1m1d142Wtd3ada3vzgqoiUEFPpjwS07O+57z5Dnved7/839VQFFcdxGoS/e7CO5IMUwLHKXR/4vb1iaN36xjvGuU+LEByudaOP/eaSo2VxM/0ofKrEU269FYJMZ7E6glNZZFbvEugmu1l8FDPdQfXMvl3S3UfryK1g2HyXSNKXkr32xENpjQlmnI5/LkR3LoPCYkXZHRMwmiB88zGcsosbfKbVOGXi9jXeUlcTaGPmwmtPM+rl4YwbUuSMOeVaAp4n+tgdp9DxF8p4lsdIyF++/HuMCFd0s1+oCVVDRBtj9xvRDTxPa0o9NrkJJTOGucFA06jCsqiH03gG6eFrVeW4q8dW5bMZIXhunf34H3AQ/Ge2xcvTiK/9k6sYNqur/sINk2jEYuMhYZo+ejFgI7FpP8aRRL2E6ieYChbwfI9+coSDqs9TfabDKbYzJfIHaih859rcjkyZ3qJX0uitltpZjOlSJvnbunyR+4rgzHmnlIdp0ylmwyeodZGZsq7djCTmX8bzF6rahU0/W+gbn6z+ZasT6ERqvF2RTAMNtSenpzbKJlpql8caFy/yu+9dVCiVJpNoNxdpmy+3+HRlxvmZa48a0NIJfr8W1dQLo3SWhXI0Nfd1P7aRPuJwMMH7pEYHs9tnrRBj4Lxjo7wW11ZAeyTAyllGSeh+fheHQuyZNRZV77eRPWRT6m1CqCL9Uz0ZeiofkJhg92Yl1SQdm9XvLFAqYaMyPHBgm90YixzIgxZEU3xyIMOYnWITPn5Xq8TfMpqIt4VrooW+Gn6sOVTFy8gsoqoc5PEXx7OckTMXyb5iNbDNgf8QizzpErrS2wLUzN3iZiX5wnuGMZJpeE5LfifXA2V1pjSoyiDK00bVAqMv0pOnY2k26LU0wZkEM2ZKESVFr8G6rxbgjiXObGvrSCWU/XMa7KKYu9RvkaD54t4dIM8j0ZunafZM7G+aTarmAQP5lvFgZ5eUycIj6qnq9hMj6BKlugkMoxlc5ibnAjzZIx17qUHFqbAdsKLxd2Hcf9+Fy8m0TB14SYODNC7EgvrqUO/OLU+m37UbReGWPYKgpSyeRwlmI2r+SYxvdCLYMHLuJ8qgp1SI/zuWoMDpPyvWsoysiKHTMt9jDROYrvmTBZsSP9X7UTen0Rkf2XhFkNkRlMMTVcoP3VZmF6Jn7dfhx7lZ/IgV9mMgkMfifjrXESp2eUETsVwbFxIbHP2ilv9NH7STs4RStmVWiMauInRpGdJnRCkRqbDr3PRc/eFsLvr6Z92/cwVVBknevKkOlJYqoSxvxzktGjfRTzGjSSsDubhYjYbf/WBq62xND5nCR/7EPylKERnT52dkRZy3QOoyhy/Id+JMlE5IMWqnYtp+PdFrKDaSXmjhroK5uXkR9Pc6wtytnOGSnejPLHKil0p0me+2fx/xVDowub00L0cHfpCfwOXGuNhkkdS3sAAAAASUVORK5CYII=");
    }

    @SpringBootApplication
    public static class App {
        public static void main(String[] args) {
            SpringApplication.run(App.class, args);
        }
    }
}
