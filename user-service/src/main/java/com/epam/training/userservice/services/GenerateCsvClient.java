package com.epam.training.userservice.services;

public interface GenerateCsvClient {

    /**
     * Sends pattern to the generator to generate the file
     *
     * @param pattern
     * @return path
     */
    String sendToGenerate(String[] pattern);
}
