package user_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user_service.dao.CsvFileMapper;
import user_service.dao.CsvTemplatesMapper;
import user_service.entities.CsvFile;
import user_service.entities.CsvTemplate;

import java.util.List;


@Component
class CsvManagementServiceImpl implements CsvManagementService {

    @Autowired
    private CsvTemplatesMapper csvTemplatesMapper;

    @Autowired
    private CsvFileMapper csvFileMapper;

    @Autowired
    private JobService jobService;

    @Autowired
    private GenerateCsvConnector generateCsvConnector;

    public CsvManagementServiceImpl(CsvTemplatesMapper csvTemplatesMapper, CsvFileMapper csvFileMapper, JobService jobService, GenerateCsvConnector generateCsvConnector) {
        this.csvTemplatesMapper = csvTemplatesMapper;
        this.csvFileMapper = csvFileMapper;
        this.jobService = jobService;
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
        jobService.createGenerateCsvJob(userId, pattern);
    }

    @Override
    public List<CsvFile> findAll(String userId) {

        return csvFileMapper.findAllUserFiles(userId);
    }

}
