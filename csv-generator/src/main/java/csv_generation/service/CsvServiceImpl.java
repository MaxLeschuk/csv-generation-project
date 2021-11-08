package csv_generation.service;

import csv_generation.generator.CsvGeneratorImpl;
import csv_generation.resource.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsvServiceImpl {

    @Autowired
    private StorageServiceImpl storageService;

    @Autowired
    private CsvGeneratorImpl csvGenerator;

    public String create(String[] columns) {
        String path = csvGenerator.generate(columns);
        return "yes";

    }
}
