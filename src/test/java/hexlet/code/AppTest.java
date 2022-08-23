package hexlet.code;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    public void appTest() {
        App app = new App();
        CommandLine cmd = new CommandLine(app);
        int exitCode = cmd.execute("./src/test/resources/file.json", "./src/test/resources/file1.json");
        assertEquals(0, exitCode);
    }
}