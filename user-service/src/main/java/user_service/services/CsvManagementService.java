package user_service.services;

import user_service.entities.CsvFile;

import java.util.List;


public interface CsvManagementService {

    /**
     * Creates new CsvFile using template by id
     *
     * @param id {@link user_service.entities.CsvTemplate} id
     */
    void create(Integer id);

    /**
     * Returns all CsvFiles
     *
     * @return
     */
    List<CsvFile> findAll();

    /**
     * Save CsvFile data
     *
     * @param path of file
     */
    void saveFileData(String path);
}
