package csv_generation.service;

import csv_generation.exceptions.TechnicalException;
import csv_generation.generator.CsvGeneratorImpl;
import csv_generation.resource.StorageService;
import org.jobrunr.jobs.lambdas.JobLambda;
import org.jobrunr.scheduling.JobScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CsvServiceImplTest {


    @Mock
    private StorageService storageService;
    @Mock
    private CsvGeneratorImpl csvGenerator;

    @Mock
    private JobScheduler jobScheduler;
    @InjectMocks
    private CsvServiceImpl csvService;

    private final String path = "src/test/resources/test.csv";

    @Test
    void test_create()  {
        String[] template = new String[]{"col1", "col2"};
        given(csvGenerator.generate(template)).willReturn(path);
        String path1 = csvService.create(template);
        assertEquals(path, path1);
        verify(jobScheduler, times(1)).enqueue(any(JobLambda.class));
    }

    @Test
    void test_throwing_exception() {
        String[] template = new String[]{"col1", "col2"};
        given(csvGenerator.generate(template)).willReturn("path");
        assertThrows(TechnicalException.class, () -> csvService.create(template));
    }
}