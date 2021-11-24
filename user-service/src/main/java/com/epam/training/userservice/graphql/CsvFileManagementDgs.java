package com.epam.training.userservice.graphql;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import com.epam.training.userservice.entities.CsvFile;
import com.epam.training.userservice.services.CsvManagementService;

import java.util.List;

@DgsComponent
public class CsvFileManagementDgs {

    @Autowired
    private CsvManagementService csvManagementService;

    @DgsMutation
    public List<CsvFile> createCsvFile(@InputArgument(name = "input") Integer id) {
        try {
            csvManagementService.startFileGenerateCsvJob(getPrincipalName(), id);
            return csvManagementService.findAll(getPrincipalName());
        } catch (NullPointerException e) {
            throw new DgsEntityNotFoundException("Pattern with this id was not found");
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
