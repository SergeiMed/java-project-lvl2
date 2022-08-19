package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


class DifferTest {

    @Test
    public void testDiffer() throws IOException {
        File file = new File("src/test/resources/file.json");
        File file1 = new File("src/test/resources/file1.json");
        FileWriter writer = new FileWriter(file);
        FileWriter writer1 = new FileWriter(file1);
        writer.write("{\n"
                + "  \"host\": \"hexlet.io\",\n"
                + "  \"timeout\": 50,\n"
                + "  \"proxy\": \"123.234.53.22\",\n"
                + "  \"follow\": false\n"
                + "}");
        writer1.write("{\n"
                + "  \"timeout\": 20,\n"
                + "  \"verbose\": true,\n"
                + "  \"host\": \"hexlet.io\"\n"
                + "}");
        writer1.flush();
        writer1.close();
        writer.flush();
        writer.close();
        String actual = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}";
        Assertions.assertEquals(Differ.generate(file, file1), actual);
    }
}