package user_service.graphql;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;
import user_service.entities.CsvFile;
import user_service.services.CsvManagementService;

import java.util.List;

@DgsComponent
public class CsvFileManagementDgs {

    @Autowired
    private CsvManagementService csvManagementService;

    @DgsMutation
    public List<CsvFile> create(@InputArgument(name = "id") Integer id) {
        csvManagementService.create(id);
        return csvManagementService.findAll();

    }
}
