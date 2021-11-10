package user_service.services;

import org.jobrunr.jobs.annotations.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class GenerateCsvConnectorImpl implements GenerateCsvConnector {
    private String getUrl() {
        return "http://localhost:8081/csv-generator/generate";
    }


    @Autowired
    private RestTemplate restTemplate;

    @Job(name = "Send request with pattern to csv generator to generate csv")
    public String sendToGenerate(String[] pattern) {
        String result = restTemplate.postForObject(getUrl(), pattern, String.class);
        return result;
    }
}
