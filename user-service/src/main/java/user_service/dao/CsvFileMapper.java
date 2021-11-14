package user_service.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import user_service.entities.CsvFile;

import java.util.List;

@Mapper
public interface CsvFileMapper {


    /**
     * Creates new {@link CsvFile}
     */
    @Insert("insert into t_csv_files(userId,path) values(#{userId},#{path})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void create(CsvFile csvFile);

    /**
     * Returns all user files by his id
     */
    @Select("select * from t_csv_files where userId=#{userId}")
    List<CsvFile> findAllUserFiles(String userId);
}
