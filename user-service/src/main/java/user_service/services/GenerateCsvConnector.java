package user_service.services;

public interface GenerateCsvConnector {

    /**
     * Sends pattern to the generator to generate the file
     *
     * @param pattern
     * @return path
     */
    String sendToGenerate(String[] pattern);
}
