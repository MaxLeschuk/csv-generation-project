package com.epam.training.csvgeneration.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "props")
public class ConfigProperties {
    private Integer generateRowCount;
    private String generatePath;
}
