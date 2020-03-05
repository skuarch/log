package app.structure;

import app.structure.bean.Log;
import app.structure.service.CVSService;
import app.structure.service.FileService;
import app.structure.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ReadLogs implements CommandLineRunner {

    @Value("${app.logs.folder}")
    private String logsFolder;

    @Autowired
    private FileService fileService;

    @Autowired
    private CVSService cvsService;

    @Autowired
    private LogService logService;

    @Override
    public void run(String... args) throws Exception {

        Set<String> fileNames = fileService.getFileNames(logsFolder);
        Set<String> fileNamesAndPath = fileService.setPathToFileNames(fileNames);
        List<Log> logs = cvsService.readCvs(fileNamesAndPath);
        logService.orderList(logs);

        logs.forEach(log -> System.out.println(log.getDate() + " " + log.getMessage()));

    }
}
