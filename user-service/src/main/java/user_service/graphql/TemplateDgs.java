package user_service.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.keycloak.adapters.BearerTokenRequestAuthenticator;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import user_service.entities.CsvTemplate;
import user_service.services.TemplateService;

import java.security.Principal;
import java.util.List;

@DgsComponent
public class TemplateDgs {

    private SecurityContextHolder securityContext;

    @Autowired
    private TemplateService templateService;


    @DgsQuery
    public List<CsvTemplate> getAllTemplates() {
        BearerTokenRequestAuthenticator bearerTokenRequestAuthenticator;
       return templateService.getAllTemplates();
    }

}
