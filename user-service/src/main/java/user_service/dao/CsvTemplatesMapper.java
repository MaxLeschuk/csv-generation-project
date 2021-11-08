package user_service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import user_service.config.type_handlers.StringToStringArrayHandler;
import user_service.entities.CsvTemplate;

import java.util.List;

@Mapper
public interface CsvTemplatesMapper {


    /**
     * Returns all {@link CsvTemplate}
     */
    @Select("select * from t_csv_templates;")
    @Results(value = {
            @Result(property = "columns",
                    javaType = String[].class,
                    jdbcType = JdbcType.VARCHAR,
                    typeHandler = StringToStringArrayHandler.class,
                    column = "template")
    })
    List<CsvTemplate> findAll();


    /**
     * Returns CsvTemplate by id
     */
    @Select("select * from t_csv_template where id=#{id}")
    CsvTemplate findById(Integer id);


}
