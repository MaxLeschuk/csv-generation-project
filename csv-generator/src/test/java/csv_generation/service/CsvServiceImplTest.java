package csv_generation.service;

import csv_generation.exceptions.TechnicalException;
import csv_generation.generator.CsvGeneratorImpl;
import csv_generation.resource.StorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xmlunit.builder.Input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CsvServiceImplTest {


    @Mock
    private StorageService storageService;
    @Mock
    private CsvGeneratorImpl csvGenerator;


    @InjectMocks
    private CsvServiceImpl csvService;

    private final String path = "target/test";

    @Test
    void test_create_without_exceptions() throws IOException {
        File file = new File(path);
        file.createNewFile();
        String[] template = new String[]{"col1", "col2"};
        given(csvGenerator.generate(template)).willReturn(path);
        given(storageService.save(any(InputStream.class))).willReturn("path1");
        String path1 = csvService.create(template);
        assertEquals("path1", path1);
    }

    @Test
    void test_throwing_exception() {
        String[] template = new String[]{"col1", "col2"};
        given(csvGenerator.generate(template)).willReturn("path");
        assertThrows(TechnicalException.class, () -> csvService.create(template));
    }
}