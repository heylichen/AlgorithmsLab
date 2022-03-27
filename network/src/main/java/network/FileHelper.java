package network;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileHelper {

    public static List<String> readLines(String cp) {
        ClassPathResource cpr = new ClassPathResource(cp);
        try {
            return FileUtils.readLines(cpr.getFile(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
