package com.epam.training.csvgeneration.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "props")
public class ConfigProperties {
    private Integer generatorRowCount;
    private String generatorPath;
    private String s3Url;
    private String s3Bucket;
    private String s3AccessKey;
    private String s3SecretKey;
}
