package cleancode.studycafe.tobe.io.provider.support;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvFileReadSupport {

    public static final String CSV_FILE_ROOT_PATH = "readable-code/src/main/resources/cleancode/studycafe/";
    public static final String CSV_SPLITTER = ",";

    private CsvFileReadSupport() {
    }

    public static List<String> readAllLinesBy(String fileName) throws IOException {
        Path path = getPath(fileName);
        return Files.readAllLines(path);
    }

    private static Path getPath(String fileName) {
        return Paths.get(CSV_FILE_ROOT_PATH + fileName);
    }
}
