package com.epam.training.csvgeneration.generator;

public interface CsvGenerator {


    /**
     * Generates files using pattern
     *
     * @param columns pattern
     * @return path
     */
    String generate(String[] columns);


}
