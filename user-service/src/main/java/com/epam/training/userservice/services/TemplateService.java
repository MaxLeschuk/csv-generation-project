package com.epam.training.userservice.services;

import com.epam.training.userservice.entities.CsvTemplate;

import java.util.List;

public interface TemplateService {

    /**
     * Returns all {@link CsvTemplate}
     *
     * @return
     */
    List<CsvTemplate> getAllTemplates();
}
