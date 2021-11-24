package user_service.graphql;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import user_service.entities.CsvFile;
import user_service.services.CsvManagementService;

import java.security.Principal;
import java.util.List;

@DgsComponent
public class CsvFileManagementDgs {

    @Autowired
    private CsvManagementService csvManagementService;

    @DgsMutation
    public List<CsvFile> createCsvFile(@InputArgument(name = "input") Integer id) {
        try {
            csvManagementService.create(getPrincipalName(), id);
            return csvManagementService.findAll(getPrincipalName());
        } catch (NullPointerException e) {
            throw new DgsEntityNotFoundException();
        }

    }

    @DgsQuery
    public List<CsvFile> getAllCsvFiles() {
        return csvManagementService.findAll(getPrincipalName());
    }

    private String getPrincipalName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
