package deepikavasudevan.project;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesOperatorTests {

    String filename = "sample.txt";
    FilesOperator filesOperator = new FilesOperator(filename);

    @Test
    public void createFileAndWritesIntoIt() {
        filesOperator.writeToFile("Hello there");
        Path path = Paths.get(filename);
        Assert.assertTrue(Files.exists(path));
    }

    @Test
    public void readFromFileAndDisplaysContents() {
        filesOperator.writeToFile("Hello there");
        String contents = filesOperator.getContentsFromFile();
        Assert.assertEquals("[Hello there]", contents);
    }

    @After
    public void cleanUp() throws IOException {
        Path path = Paths.get(filename);
        Files.deleteIfExists(path);
    }
}
