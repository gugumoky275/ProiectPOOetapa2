package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class InputLoader {
    private static InputLoader instance = null;

    private InputLoader() { }

    /**
     * Get instance of Singleton class InputLoader
     * @return the instance
     */
    public static InputLoader getInstance() {
        if (instance == null) {
            instance = new InputLoader();
        }
        return instance;
    }

    /**
     * Reads the data
     * @param path path to input file
     * @return the parsed input
     * @throws IOException IOException
     */
    public Input readData(final String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path), Input.class);
    }
}
