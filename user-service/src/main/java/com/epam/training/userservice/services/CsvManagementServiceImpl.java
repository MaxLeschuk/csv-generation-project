package com.epam.training.userservice.services;

import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.epam.training.userservice.dao.CsvFileRepository;
import com.epam.training.userservice.dao.CsvTemplatesRepository;
import com.epam.training.userservice.entities.CsvFile;
import com.epam.training.userservice.entities.CsvTemplate;

import java.util.List;


@Component
public class CsvManagementServiceImpl implements CsvManagementService {

    @Autowired
    private CsvTemplatesRepository csvTemplatesMapper;

    @Autowired
    private CsvFileRepository csvFileMapper;

    @Autowired
    private JobScheduler jobScheduler;

    @Autowired
    private GenerateCsvClient generateCsvConnector;

    public CsvManagementServiceImpl(CsvTemplatesRepository csvTemplatesMapper, CsvFileRepository csvFileMapper, JobScheduler jobScheduler, GenerateCsvClient generateCsvConnector) {
        this.csvTemplatesMapper = csvTemplatesMapper;
        this.csvFileMapper = csvFileMapper;
        this.jobScheduler = jobScheduler;
        this.generateCsvConnector = generateCsvConnector;
    }

    public void generateCsv(String userId, String[] columns) {
        String path = generateCsvConnector.sendToGenerate(columns);
        if (path != null)
            csvFileMapper.save(new CsvFile(userId, path));
    }

    @Override
    public void startFileGenerateCsvJob(String userId, Integer id) {
        CsvTemplate pattern = csvTemplatesMapper.findById(id);
        if (pattern == null)
            throw new NullPointerException();
       createGenerateCsvJob(userId, pattern);
    }

    @Override
    public List<CsvFile> findAll(String userId) {

        return csvFileMapper.findAllUserFiles(userId);
    }

    private void createGenerateCsvJob(String userId, CsvTemplate csvTemplate) {
        jobScheduler.enqueue(() -> generateCsv(userId,csvTemplate.getColumns()));
    }

}
