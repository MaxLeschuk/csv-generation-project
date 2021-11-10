package user_service.services;

import user_service.entities.CsvTemplate;

public interface JobService {
    void createGenerateCsvJob(String userId, CsvTemplate csvTemplate);
}
