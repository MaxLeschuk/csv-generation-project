package com.epam.training.csvgeneration.generator;

import com.epam.training.csvgeneration.config.ConfigProperties;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;


@SpringBootTest(classes = CsvGeneratorImpl.class)
class CsvGeneratorImplTest {


    @Autowired
    private CsvGeneratorImpl csvGenerator;

    @MockBean
    private ConfigProperties configProperties;

    @Test
    void test_generate() throws IOException, CsvException {
        given(configProperties.getGeneratorPath()).willReturn("target/");
        given(configProperties.getGeneratorRowCount()).willReturn(3);
        String path = csvGenerator.generate(new String[]{"col1", "col2"});
        CSVReader csvReader = new CSVReader(new FileReader(path));

        List<String[]> rows = csvReader.readAll();
        assertEquals(3, rows.size());
        assertEquals(2, rows.get(0).length);
        assertEquals(2, rows.get(1).length);
        assertEquals(2, rows.get(2).length);
    }


}