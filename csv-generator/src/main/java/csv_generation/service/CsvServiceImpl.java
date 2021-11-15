package csv_generation.service;

import csv_generation.exceptions.TechnicalException;
import csv_generation.generator.CsvGeneratorImpl;
import csv_generation.resource.StorageService;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

@Component
class CsvServiceImpl implements CsvService {

    private Logger logger = Logger.getLogger(CsvServiceImpl.class.getName());
    @Autowired
    private StorageService storageService;
    @Autowired
    private JobScheduler jobScheduler;
    @Autowired
    private CsvGeneratorImpl csvGenerator;

    @Override
    public String create(String[] columns) {
        String path = csvGenerator.generate(columns);
        try {
            storageService.save(new FileInputStream(path));
            jobScheduler.enqueue(() -> Files.delete(Path.of(path)));
            return path;
        } catch (FileNotFoundException e) {
            throw new TechnicalException(e.getLocalizedMessage());
        }
    }
}
