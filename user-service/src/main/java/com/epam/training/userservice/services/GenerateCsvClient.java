package com.epam.training.userservice.services;

public interface GenerateCsvClient {

    /**
     * Sends pattern to the generator to generate the file
     */
    void sendToGenerate(String userId, String[] pattern);
}
