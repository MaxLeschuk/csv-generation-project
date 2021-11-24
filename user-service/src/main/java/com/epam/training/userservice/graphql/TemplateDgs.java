package com.epam.training.userservice.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import com.epam.training.userservice.entities.CsvTemplate;
import com.epam.training.userservice.services.TemplateService;

import java.util.List;

@DgsComponent
public class TemplateDgs {

    @Autowired
    private TemplateService templateService;


    @DgsQuery
    public List<CsvTemplate> getAllTemplates() {
        return templateService.getAllTemplates();
    }

}
