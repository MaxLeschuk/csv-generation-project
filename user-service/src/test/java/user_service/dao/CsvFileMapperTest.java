package user_service.dao;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import user_service.entities.CsvFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
class CsvFileMapperTest {


    @Autowired
    private CsvFileMapper csvFileMapper;

    @Test
    void test_create_and_findAll() {
        String user1 = "user1";
        String user2 = "user2";
        csvFileMapper.create(new CsvFile(user1, "user1File"));
        csvFileMapper.create(new CsvFile(user1, "user1File"));
        csvFileMapper.create(new CsvFile(user2, "user2File"));

        List<CsvFile> user1Files = csvFileMapper.findAllUserFiles(user1);
        assertEquals(2, user1Files.size());
        assertEquals("user1File", user1Files.get(0).getPath());

        List<CsvFile> user2Files = csvFileMapper.findAllUserFiles(user2);
        assertEquals(1, user2Files.size());
        assertEquals("user2File", user2Files.get(0).getPath());

    }
}