package com.epam.training.userservice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import com.epam.training.userservice.mybatis.type_handlers.StringToStringArrayHandler;
import com.epam.training.userservice.entities.CsvTemplate;

import java.util.List;

@Mapper
public interface CsvTemplatesRepository {


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
    @Select("select * from t_csv_templates where id=#{id}")
    @Results(value = {
            @Result(property = "columns",
                    javaType = String[].class,
                    jdbcType = JdbcType.VARCHAR,
                    typeHandler = StringToStringArrayHandler.class,
                    column = "template")
    })
    CsvTemplate findById(Integer id);


}
