import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestClass {

    @Test
    public void test() throws IOException {
        //Given
        File file = new File("pom.xml");

        //When
        String content = Files.readString(file.toPath());


        //Then
        Assertions.assertNotNull(content);
    }
}
