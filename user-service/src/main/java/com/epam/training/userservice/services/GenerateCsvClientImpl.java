package com.epam.training.userservice.services;

import com.epam.training.userservice.config.ConfigProperties;
import com.epam.training.userservice.dao.CsvFileRepository;
import com.epam.training.userservice.entities.CsvFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
class GenerateCsvClientImpl implements GenerateCsvClient {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private CsvFileRepository csvFileRepository;

    private void saveFileCsv(String userId, String path) {
        if (path != null)
            csvFileRepository.save(new CsvFile(userId, path));
    }

    public void sendToGenerate(String userId, String[] pattern) {
        Flux<String> resultFlux = WebClient
                .create(configProperties.getGeneratorUrl())
                .post()
                .body(Mono.just(pattern), String[].class)
                .retrieve()
                .bodyToFlux(String.class);
        resultFlux.subscribe(result -> saveFileCsv(userId, result));
        System.out.println("path");
    }
}
