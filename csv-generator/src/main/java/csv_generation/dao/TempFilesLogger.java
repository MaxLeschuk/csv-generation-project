package csv_generation.dao;


import csv_generation.entity.TempFileInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TempFilesLogger {


    void create(TempFileInfo tempFileInfo);

}
