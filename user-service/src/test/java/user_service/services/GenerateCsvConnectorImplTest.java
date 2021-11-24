package user_service.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@TestPropertySource(properties = "generator.url=url")
@ExtendWith(MockitoExtension.class)
class GenerateCsvConnectorImplTest {

    @InjectMocks
    private GenerateCsvConnectorImpl csvConnector;


    @Mock
    private RestTemplate restTemplate;


    @Test
    void test_send_to_generate() {
        ReflectionTestUtils.setField(csvConnector,"generatorUrl","url");
        String[] template = new String[]{"1"};
        given(restTemplate.postForObject("url", template, String.class)).willReturn("path");
        assertEquals("path", csvConnector.sendToGenerate(template));

    }
}