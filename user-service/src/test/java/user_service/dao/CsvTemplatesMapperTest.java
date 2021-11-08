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
    void findALl() {
        List<CsvTemplate> csvTemplateList = csvTemplatesMapper.findAll();
        assertEquals(4, csvTemplateList.size());
    }
}