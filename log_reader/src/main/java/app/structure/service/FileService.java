package app.structure.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {

    @Value("${app.logs.folder}")
    private String logsFolder;

    public Set<String> getFileNames(String folder) {
        if(folder == null) {
            throw new IllegalArgumentException("folder is null");
        }
        if(folder.isEmpty()) {
            throw new IllegalArgumentException("folder is empty");
        }
        return Stream.of(new File(folder).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    public Set<String> setPathToFileNames(Set<String> fileNames) {
        return fileNames
            .stream()
            .map(fileName -> logsFolder + "/" + fileName)
            .collect(Collectors.toSet());
    }

}
