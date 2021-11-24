package csv_generation.service;

import csv_generation.exceptions.TechnicalException;
import csv_generation.generator.CsvGeneratorImpl;
import csv_generation.resource.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

@Component
class CsvServiceImpl implements CsvService {

    private Logger logger = Logger.getLogger(CsvServiceImpl.class.getName());
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
            inputStream.close();
            Files.delete(Path.of(tempPath));
            return path;
        } catch (IOException e) {
            if (path == null)
                throw new TechnicalException(e.getLocalizedMessage());
            logger.info(e.getMessage());
            return path;
        }
    }
}
