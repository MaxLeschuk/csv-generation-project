package user_service.services;

import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user_service.entities.CsvTemplate;

@Component
class JobServiceImpl implements JobService {

    @Autowired
    private JobScheduler jobScheduler;

    @Autowired
    private CsvManagementService csvManagementService;

    @Override
    public void createGenerateCsvJob(String userId, CsvTemplate csvTemplate) {
        jobScheduler.enqueue(() -> csvManagementService.generateCsv(userId, csvTemplate.getColumns()));
    }
}
