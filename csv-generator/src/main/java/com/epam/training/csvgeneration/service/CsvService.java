package com.epam.training.csvgeneration.service;

public interface CsvService {


    /**
     * Creates new CsvFile.
     *
     * @param columns pattern
     * @return path
     */
    String create(String[] columns);
}
