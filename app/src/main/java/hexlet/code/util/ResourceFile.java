package hexlet.code.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;


public class ResourceFile {
    public static String readResourceFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/fixtures/samplepage.html"));
        String lineOfFile = reader.readLine();
        var result = new StringJoiner("\n");

        while (lineOfFile != null) {
            result.add(lineOfFile);
            lineOfFile = reader.readLine();
        }
        return result.toString();
    }
}
