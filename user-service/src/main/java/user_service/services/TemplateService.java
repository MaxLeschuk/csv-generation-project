package user_service.services;

import user_service.entities.CsvTemplate;

import java.util.List;

public interface TemplateService {

    /**
     * Returns all {@link CsvTemplate}
     *
     * @return
     */
    List<CsvTemplate> getAllTemplates();
}
