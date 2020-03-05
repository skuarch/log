package app.structure.service;

import app.structure.bean.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CVSServiceTest {

    @Autowired
    private CVSService cvsService;

    @Value("${app.logs.folder}")
    private String logsFolder;

    @BeforeEach
    public void setUp(){
        assertNotNull(cvsService);
        assertNotNull(logsFolder);
    }

    @Test
    void readCvs() throws IOException {
        // Given
        File file = new File(
                getClass()
                .getClassLoader()
                .getResource("server-cuyew12x.log")
                .getFile()
        );

        // when
        List<Log> logs = cvsService.readCvs(file.getAbsolutePath());

        // then
        assertNotNull(logs);
    }

    @Test
    void readCvsWithAbsolutePath() throws IOException {
        // Given
        String file = logsFolder + "/server-cuyew12x.log";

        // when
        List<Log> logs = cvsService.readCvs(file);

        // then
        assertNotNull(logs);
    }

    @Test
    void readCvsCheckDate() throws IOException {
        // Given
        String file = logsFolder + "/server-cuyew12x.log";

        // when
        List<Log> logs = cvsService.readCvs(file);

        // then
        String date = logs.get(0).getDate();
        assertEquals(date, "2016-12-20T19:01:16Z");
    }

    @Test
    void readCvsCheckMessage() throws IOException {
        // Given
        String file = logsFolder + "/server-cuyew12x.log";

        // when
        List<Log> logs = cvsService.readCvs(file);

        // then
        String message = logs.get(0).getMessage();
        assertEquals(message, " Server B started."); // message contains spaces
    }

    @Test
    void readCvsNullParameter() {
        // Given
        String fileName = null;

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            List<Log> logs = cvsService.readCvs(fileName);
        });

        // then
        assertNotNull(exception.getMessage());

    }
}