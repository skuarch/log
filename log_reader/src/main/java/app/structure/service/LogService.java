package app.structure.service;

import app.structure.bean.Log;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class LogService {

    public void orderList(List<Log> logs){
        logs.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
    }

}
