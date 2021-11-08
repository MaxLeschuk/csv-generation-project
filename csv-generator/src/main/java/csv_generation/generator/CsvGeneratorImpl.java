package csv_generation.generator;

import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class CsvGeneratorImpl implements CsvGenerator {

    private Logger logger = Logger.getLogger(CsvGeneratorImpl.class.getName());


    @Value("${lines.size}")
    private Integer size;

    @Override
    public String generate(String[] columns) {
        String path = generateTempFile();
        try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            for (int i = 0; i < size; i++) {
                writer.writeNext(generateField(i, columns));
            }
            return path;
        } catch (IOException e) {
            logger.info(e.getLocalizedMessage());
        }

        return null;
    }

    private String[] generateField(int num, String[] columns) {
        String[] field = new String[columns.length];
        for (int j = 0; j < field.length; j++) {
            field[j] = columns[j] + num;
        }
        return field;
    }

    private String generateTempFile() {
        String path = "C:\\Users\\Maksim_Liashchuk\\Desktop\\" + UUID.randomUUID();
        return path;
    }
}
