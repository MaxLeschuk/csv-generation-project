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
    public List<CsvFile> createCsvFile(@InputArgument(name = "id") Integer id) {
        try {
            csvManagementService.create(getPrincipal().getName(), id);
            return csvManagementService.findAll(getPrincipal().getName());
        } catch (NullPointerException e) {
            throw new DgsEntityNotFoundException();
        }

    }

    @DgsQuery
    public List<CsvFile> getAllCsvFiles() {
        return csvManagementService.findAll(getPrincipal().getName());
    }

    private Principal getPrincipal() {
        return (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
