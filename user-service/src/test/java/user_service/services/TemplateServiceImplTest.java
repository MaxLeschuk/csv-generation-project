package user_service.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import user_service.dao.CsvTemplatesMapper;
import user_service.entities.CsvTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
class TemplateServiceImplTest {

    @Autowired
    private CsvTemplatesMapper csvTemplatesMapper;
    private TemplateServiceImpl templateService;

    @BeforeEach
    void before(){
        templateService = new TemplateServiceImpl(csvTemplatesMapper);
    }
    @Test
    void test_find_all() {
        List<CsvTemplate> csvTemplateList = templateService.getAllTemplates();
        assertEquals(4, csvTemplateList.size());
    }

}