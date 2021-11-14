package user_service.dao;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import user_service.entities.CsvTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
class CsvTemplatesMapperTest {


    @Autowired
    private CsvTemplatesMapper csvTemplatesMapper;

    @Test
    void test_findAll_and_findById() {
        List<CsvTemplate> csvTemplateList = csvTemplatesMapper.findAll();
        assertEquals(4, csvTemplateList.size());
        CsvTemplate mustBeFound = csvTemplateList.get(0);
        CsvTemplate csvTemplate = csvTemplatesMapper.findById(mustBeFound.getId());
        assertEquals(csvTemplate.getId(), mustBeFound.getId());
        assertEquals(csvTemplate.getColumns().length, mustBeFound.getColumns().length);
    }
}