package com.epam.training.userservice.services;

import com.epam.training.userservice.dao.CsvFileRepository;
import com.epam.training.userservice.dao.CsvTemplatesRepository;
import com.epam.training.userservice.entities.CsvFile;
import org.jobrunr.jobs.lambdas.JobLambda;
import org.jobrunr.scheduling.JobScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@MybatisTest
class CsvManagementServiceImplTest {

    @Autowired
    private CsvFileRepository csvFileMapper;

    @Autowired
    private CsvTemplatesRepository csvTemplatesMapper;

    private CsvManagementServiceImpl csvManagementService;

    @Mock
    private JobScheduler jobScheduler;

    @Mock
    private GenerateCsvClient generateCsvConnector;

    @BeforeEach
    void before() {
        csvManagementService = new CsvManagementServiceImpl(csvTemplatesMapper, csvFileMapper
                , jobScheduler, generateCsvConnector);
        csvFileMapper.save(new CsvFile("user1", "path"));

        csvFileMapper.save(new CsvFile("user2", "path"));
        csvFileMapper.save(new CsvFile("user2", "path"));
    }

    @Test
    void test_find_all() {
        List<CsvFile> files = csvManagementService.findAll("user1");
        assertEquals(1, files.size());

    }

    @Test
    void test_generate_csv() {
        String[] testPattern = new String[]{"1", "2"};
        given(generateCsvConnector.sendToGenerate(testPattern)).willReturn("path2");
        csvManagementService.generateCsv("user3", testPattern);
        assertEquals("path2", csvManagementService.findAll("user3").get(0).getPath());
    }

    @Test
    void test_generate_csv_if_generator_not_create() {
        String[] testPattern = new String[]{"1", "2"};
        csvManagementService.generateCsv("user", testPattern);
        List<CsvFile> files = csvManagementService.findAll("user");
        assertEquals(true, files.isEmpty());
    }

    @Test
    void test_create() {
        csvManagementService.startFileGenerateCsvJob("user1", 1);
        verify(jobScheduler, times(1)).enqueue(any(JobLambda.class));
    }

    @Test
    void test_create_if_template_not_found() {
        assertThrows(NullPointerException.class, () -> csvManagementService.startFileGenerateCsvJob("user1", 1000));
    }
}