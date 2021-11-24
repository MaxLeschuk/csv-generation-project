package com.epam.training.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.epam.training.userservice.dao.CsvTemplatesRepository;
import com.epam.training.userservice.entities.CsvTemplate;

import java.util.List;

@Component
class TemplateServiceImpl implements TemplateService {

    @Autowired
    private CsvTemplatesRepository csvTemplatesMapper;

    public TemplateServiceImpl(CsvTemplatesRepository csvTemplatesMapper) {
        this.csvTemplatesMapper = csvTemplatesMapper;
    }

    public List<CsvTemplate> getAllTemplates() {
        return csvTemplatesMapper.findAll();
    }


}
