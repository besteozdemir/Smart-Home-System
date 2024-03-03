import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReading {
    public static String[] readFile(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            List<String> nonEmptyLines = new ArrayList<>();
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    nonEmptyLines.add(line);
                }
            }
            if (!nonEmptyLines.get(nonEmptyLines.size() - 1).equals("ZReport")) {
                nonEmptyLines.add("ZReport");
            }
            return nonEmptyLines.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
