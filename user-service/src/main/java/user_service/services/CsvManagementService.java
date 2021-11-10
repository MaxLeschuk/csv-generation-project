package user_service.services;

import user_service.entities.CsvFile;

import java.util.List;


public interface CsvManagementService {

    /**
     * Creates new CsvFile using template by id
     *
     * @param userId userId
     * @param id     {@link user_service.entities.CsvTemplate} id
     */
    void create(String userId, Integer id);


    /**
     * Generates new CsvFile using userId and template
     */
    void generateCsv(String userId, String[] template);

    /**
     * Returns all user CsvFiles by his id
     *
     * @return
     */
    List<CsvFile> findAll(String userId);

    /**
     * Save CsvFile data
     *
     * @param path of file
     */
    void saveFileData(String path);
}
