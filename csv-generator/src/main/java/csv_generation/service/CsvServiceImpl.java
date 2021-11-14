package csv_generation.service;

import csv_generation.generator.CsvGeneratorImpl;
import csv_generation.resource.StorageServiceImpl;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

@Component
public class CsvServiceImpl {

    private Logger logger = Logger.getLogger(CsvServiceImpl.class.getName());
    @Autowired
    private StorageServiceImpl storageService;
    @Autowired
    private JobScheduler jobScheduler;
    @Autowired
    private CsvGeneratorImpl csvGenerator;

    public String create(String[] columns) {
        String path = csvGenerator.generate(columns);
        try {
            storageService.save(new FileInputStream(path));
            jobScheduler.enqueue(() -> Files.delete(Path.of(path)));
        } catch (FileNotFoundException e) {
            logger.info(e.getLocalizedMessage());
        }
        return null;
    }
}
