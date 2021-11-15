package csv_generation.generator;

import com.opencsv.CSVWriter;
import csv_generation.exceptions.TechnicalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class CsvGeneratorImpl implements CsvGenerator {

    private Logger logger = Logger.getLogger(CsvGeneratorImpl.class.getName());


    @Value("${generate.row.count}")
    private Integer size;

    @Value("${generate.path}")
    private String path;

    @Override
    public String generate(String[] columns) {
        String filePath = generateTempFile();
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            for (int i = 0; i < size; i++) {
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
        return path + UUID.randomUUID();
    }
}
