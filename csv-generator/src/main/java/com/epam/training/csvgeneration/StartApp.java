package com.epam.training.csvgeneration;


import com.epam.training.csvgeneration.config.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class StartApp {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class);

    }
}
