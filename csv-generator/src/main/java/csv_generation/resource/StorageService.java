package csv_generation.resource;

import java.io.InputStream;

public interface StorageService {


    /**
     * Saves the file.
     *
     * @return path
     */
    String save(InputStream inputStream);

}
