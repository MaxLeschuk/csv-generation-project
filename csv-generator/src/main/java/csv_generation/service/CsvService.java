package csv_generation.service;

public interface CsvService {


    /**
     * Creates new CsvFile.
     *
     * @param columns pattern
     * @return path
     */
    String create(String[] columns);
}
