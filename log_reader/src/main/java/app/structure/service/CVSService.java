package app.structure.service;

import app.structure.bean.Log;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CVSService {

    public List<Log> readCvs(String fileName) throws IOException {

        if(fileName == null){
            throw new IllegalArgumentException("fileName is null");
        }

        FileReader filereader = new FileReader(fileName);
        List<Log> beans = new CsvToBeanBuilder(filereader)
                .withType(Log.class)
                .build()
                .parse();

        if(beans == null) {
            beans = new ArrayList<>();
        }

        return beans;
    }

    public List<Log> readCvs(Set<String> fileNames) {

        List<Log> logs = new ArrayList<>();

        fileNames.forEach(fileName -> {
            try {
                logs.addAll(readCvs(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return logs;
    }

}
