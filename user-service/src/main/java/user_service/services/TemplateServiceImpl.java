package user_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user_service.dao.CsvTemplatesMapper;
import user_service.entities.CsvTemplate;

import java.util.List;

@Component
class TemplateServiceImpl implements TemplateService {

    @Autowired
    private CsvTemplatesMapper csvTemplatesMapper;

    public List<CsvTemplate> getAllTemplates() {
        return csvTemplatesMapper.findAll();
    }


}
