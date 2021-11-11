package csv_generation.service;

public interface CsvService {


    /**
     * Creates new file.
     *
     * @param columns pattern
     * @return path
     */
    String create(String[] columns);
}
