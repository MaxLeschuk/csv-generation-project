package com.epam.training.userservice.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import com.epam.training.userservice.entities.CsvFile;
import com.epam.training.userservice.services.CsvManagementService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = {DgsAutoConfiguration.class, CsvFileManagementDgs.class})
class CsvFileManagementDgsTest {

    @MockBean
    private CsvManagementService csvManagementService;
    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @Autowired
    private CsvFileManagementDgs csvFileManagementDgs;

    private List<CsvFile> csvFileList;

    @BeforeEach
    public void before() {
        csvFileList = new ArrayList<>();
        csvFileList.add(new CsvFile("user1", "path1"));
        csvFileList.add(new CsvFile("user1", "path2"));
        csvFileList.add(new CsvFile("user1", "path3"));
    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    void test_get_all() {
        given(csvManagementService.findAll("user1")).willReturn(csvFileList);
        List<String> csvFilePaths = dgsQueryExecutor.executeAndExtractJsonPath(
                "{ getAllCsvFiles { path userId id } }", "data.getAllCsvFiles[*].path");
        assertEquals(3, csvFilePaths.size());

    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    void test_throwing_exceptions() {
        doThrow(NullPointerException.class).when(csvManagementService).startFileGenerateCsvJob("user1", 1);
        assertThrows(DgsEntityNotFoundException.class, () -> csvFileManagementDgs.createCsvFile(1));

    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    void test_create_csvFile() {
        given(csvManagementService.findAll("user1")).willReturn(csvFileList);
        List<CsvFile> csvFilesList = csvFileManagementDgs.createCsvFile(1);
        assertEquals(3, csvFilesList.size());
        verify(csvManagementService, times(1)).startFileGenerateCsvJob("user1", 1);

    }
}