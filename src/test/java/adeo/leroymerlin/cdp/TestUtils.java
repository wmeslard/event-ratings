package adeo.leroymerlin.cdp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static <T> T readJsonResource(final String path, final Class<T> clazz) throws IOException {
        final String response = getJsonContentFromFile(path);
        return objectMapper.readValue(response, clazz);
    }

    public static String getJsonContentFromFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(String.format("src/test/resources/%s", filename))));
    }

}
