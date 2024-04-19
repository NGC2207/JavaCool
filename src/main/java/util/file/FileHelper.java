package util.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

public class FileHelper {
    private static final Path problemPath = FileHelper.getRootPath().resolve("data").resolve("problem");

    public static Path getRootPath() {
        return Paths.get("").toAbsolutePath();
    }
    public static int countJsonFiles() throws IOException {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(problemPath, "*.json")) {
            return (int) StreamSupport.stream(directoryStream.spliterator(), false).count();
        }
    }

    public static void createFolder(Path path) throws IOException {
        if(isExist(path)){
            deleteFolder(path);
        }
        Files.createDirectories(path);
    }

    public static void deleteFolder(Path path) throws IOException {
        if (Files.exists(path)) {
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    public static void createFile(Path path,String code,String suffix) throws IOException {
        Path filePath=path.resolve("Main"+"."+suffix);
        Files.createFile(filePath);
        Files.write(filePath, Collections.singletonList(code), StandardCharsets.UTF_8);
    }

    public static boolean isExist(Path path){
        return Files.exists(path);
    }
}
