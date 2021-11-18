package user_service.services;

import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user_service.dao.CsvFileMapper;
import user_service.dao.CsvTemplatesMapper;
import user_service.entities.CsvFile;
import user_service.entities.CsvTemplate;

import java.util.List;


@Component
public class CsvManagementServiceImpl implements CsvManagementService {

    @Autowired
    private CsvTemplatesMapper csvTemplatesMapper;

    @Autowired
    private CsvFileMapper csvFileMapper;

    @Autowired
    private JobScheduler jobScheduler;

    @Autowired
    private GenerateCsvConnector generateCsvConnector;

    public CsvManagementServiceImpl(CsvTemplatesMapper csvTemplatesMapper, CsvFileMapper csvFileMapper, JobScheduler jobScheduler, GenerateCsvConnector generateCsvConnector) {
        this.csvTemplatesMapper = csvTemplatesMapper;
        this.csvFileMapper = csvFileMapper;
        this.jobScheduler = jobScheduler;
        this.generateCsvConnector = generateCsvConnector;
    }

    public void generateCsv(String userId, String[] template) {
        String path = generateCsvConnector.sendToGenerate(template);
        if (path != null)
            csvFileMapper.create(new CsvFile(userId, path));
    }

    @Override
    public void create(String userId, Integer id) {
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
        jobScheduler.enqueue(() -> generateCsv(userId, csvTemplate.getColumns()));
    }

}
