package csv_generation.generator;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import csv_generation.exceptions.TechnicalException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

@TestPropertySource(properties = {"generate.row.count=3", "generate.path=target/"})
@SpringBootTest(classes = CsvGeneratorImpl.class)
class CsvGeneratorImplTest {


    @Autowired
    private CsvGeneratorImpl csvGenerator;

    @Test
    void test_generate() throws IOException, CsvException {
        String path = csvGenerator.generate(new String[]{"col1", "col2"});
        CSVReader csvReader = new CSVReader(new FileReader(path));

        List<String[]> rows = csvReader.readAll();
        Assert.assertEquals(3, rows.size());
        Assert.assertEquals(2, rows.get(0).length);
        Assert.assertEquals(2, rows.get(1).length);
        Assert.assertEquals(2, rows.get(2).length);
    }


}