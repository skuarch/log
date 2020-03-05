package app.structure.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Value("${app.logs.folder}")
    private String logsFolder;

    @Test
    void getFileNames() {
        // Given

        // When
        Set<String> fileNames = fileService.getFileNames(logsFolder);

        // Then
        assertNotNull(fileNames);

    }

    @Test
    void getFileNamesByFolder() {
        // Given
        File file = new File(
                getClass()
                        .getClassLoader()
                        .getResource("server-cuyew12x.log")
                        .getFile()
        );
        String folder = file.getParent();

        // When
        Set<String> fileNames = fileService.getFileNames(folder);

        // Then
        assertTrue(fileNames.contains("server-cuyew12x.log"));

    }

    @Test
    void getFileNamesNullParameter() {
        // Given
        logsFolder = "";

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Set<String> fileNames = fileService.getFileNames(logsFolder);
        });

        // Then
        // an exception should be thrown

    }
}