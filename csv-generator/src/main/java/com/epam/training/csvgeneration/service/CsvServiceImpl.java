package com.epam.training.csvgeneration.service;

import com.epam.training.csvgeneration.exceptions.TechnicalException;
import com.epam.training.csvgeneration.generator.CsvGeneratorImpl;
import com.epam.training.csvgeneration.resource.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

@Slf4j
@Component
class CsvServiceImpl implements CsvService {

    @Autowired
    private StorageService storageService;

    @Autowired
    private CsvGeneratorImpl csvGenerator;

    @Override
    public String create(String[] columns) {
        String path = null;
        String tempPath = csvGenerator.generate(columns);
        try (InputStream inputStream = new FileInputStream(tempPath)) {
            path = storageService.save(inputStream);
            return path;
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new TechnicalException(e.getLocalizedMessage());
        } finally {
            try {
                Files.delete(Path.of(tempPath));
            } catch (IOException e) {
                if (path == null)
                    throw new TechnicalException(e.getLocalizedMessage());
                log.info(e.getMessage());
                return path;
            }
        }
    }
}
