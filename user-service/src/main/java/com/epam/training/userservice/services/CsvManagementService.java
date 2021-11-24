package com.epam.training.userservice.services;

import com.epam.training.userservice.entities.CsvFile;

import java.util.List;


public interface CsvManagementService {

    /**
     * Creates new CsvFile using template by id
     *
     * @param userId userId
     * @param id     {@link com.epam.training.userservice.entities.CsvTemplate} id
     */
    void startFileGenerateCsvJob(String userId, Integer id);


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

}
