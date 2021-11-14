package user_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class GenerateCsvConnectorImpl implements GenerateCsvConnector {


    @Value("${generator.url}")
    private String generatorUrl;


    @Autowired
    private RestTemplate restTemplate;

    public String sendToGenerate(String[] pattern) {
        return restTemplate.postForObject(generatorUrl, pattern, String.class);
    }
}
