package com.epam.training.userservice.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import com.epam.training.userservice.entities.CsvTemplate;
import com.epam.training.userservice.services.TemplateService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {DgsAutoConfiguration.class, TemplateDgs.class})
class TemplateDgsTest {
    @MockBean
    private TemplateService templateService;
    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    private List<CsvTemplate> csvTemplateList;

    @BeforeEach
    public void before() {
        csvTemplateList = new ArrayList<>();
        CsvTemplate csvTemplate = new CsvTemplate();
        csvTemplate.setId(1);
        csvTemplateList.add(csvTemplate);
    }

    @Test
    @WithMockUser(username = "user1", roles = {"USER"})
    void test_get_all() {
        given(templateService.getAllTemplates()).willReturn(csvTemplateList);
        List<String> csvFilePaths = dgsQueryExecutor.executeAndExtractJsonPath(
                "{ getAllTemplates { id } }", "data.getAllTemplates[*].id");
        assertEquals(1, csvFilePaths.size());

    }
}