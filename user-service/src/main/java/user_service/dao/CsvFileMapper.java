package user_service.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import user_service.entities.CsvFile;

import java.util.List;

@Mapper
public interface CsvFileMapper {


    /**
     * Creates new {@link CsvFile}
     */
    @Insert("insert into t_csv_files(user,csvPath) values(#{userId},#{csvPath})")
    void create(String userId, String csvPath);

    /**
     * Returns all user files by his id
     */
    @Select("select * from t_csv_files where user=#{userId}")
    List<CsvFile> findAllUserFiles(String userId);
}
