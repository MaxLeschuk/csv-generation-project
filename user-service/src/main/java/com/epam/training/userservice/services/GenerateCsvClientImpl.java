package com.epam.training.userservice.services;

import com.epam.training.userservice.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class GenerateCsvClientImpl implements GenerateCsvClient {

    @Autowired
    private ConfigProperties configProperties;


    @Autowired
    private RestTemplate restTemplate;

    public String sendToGenerate(String[] pattern) {
        return restTemplate.postForObject(configProperties.getGeneratorUrl(), pattern, String.class);
    }
}
