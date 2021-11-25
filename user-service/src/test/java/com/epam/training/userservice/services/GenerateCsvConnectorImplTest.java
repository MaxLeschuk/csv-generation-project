package com.epam.training.userservice.services;

import com.epam.training.userservice.config.ConfigProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@TestPropertySource(properties = "generator.url=url")
@ExtendWith(MockitoExtension.class)
class GenerateCsvConnectorImplTest {

    @InjectMocks
    private GenerateCsvClientImpl csvConnector;


    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ConfigProperties configProperties;

    @Test
    void test_send_to_generate() {
        String[] template = new String[]{"1"};
        given(configProperties.getGeneratorUrl()).willReturn("url");
        given(restTemplate.postForObject("url", template, String.class)).willReturn("path");
       // assertEquals("path", csvConnector.sendToGenerate(template));

    }
}