package com.epam.training.csvgeneration.generator;

import com.epam.training.csvgeneration.config.ConfigProperties;
import com.epam.training.csvgeneration.exceptions.TechnicalException;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class CsvGeneratorImpl implements CsvGenerator {

    private Logger logger = Logger.getLogger(CsvGeneratorImpl.class.getName());

    @Autowired
    private ConfigProperties configProperties;

    @Override
    public String generate(String[] columns) {
        String filePath = generateTempFile();
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            for (int i = 0; i < configProperties.getGeneratorRowCount(); i++) {
                writer.writeNext(generateField(i, columns));
            }
            return filePath;
        } catch (IOException e) {
            throw new TechnicalException(e.getLocalizedMessage());
        }
    }

    private String[] generateField(int num, String[] columns) {
        String[] field = new String[columns.length];
        for (int j = 0; j < field.length; j++) {
            field[j] = columns[j] + num;
        }
        return field;
    }

    private String generateTempFile() {
        return configProperties.getGeneratorPath() + UUID.randomUUID();
    }
}
