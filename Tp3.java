import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

public class Tp3 {

    // Debugging flag.
    static final boolean DEBUG = true;

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get(args[0]);
        Path outputPath = Paths.get(args[1]);

        // Read input.
        List<String> lines = Files.readAllLines(inputPath);

        // Do stuff.
        for (String line : lines) {
            if (DEBUG) {
                System.out.println(line);
            }
        }

        String result = "[TO BE IMPLEMENTED]";

        // Write output.
        Files.writeString(outputPath, result);

    }
}