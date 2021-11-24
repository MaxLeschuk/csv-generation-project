package user_service.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import user_service.entities.CsvTemplate;
import user_service.services.TemplateService;

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
