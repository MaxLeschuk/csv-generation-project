package com.epam.training.userservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "props")
public class ConfigProperties {
    private String generatorUrl;
}
