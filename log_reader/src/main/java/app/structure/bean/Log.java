package app.structure.bean;
import com.opencsv.bean.CsvBindByPosition;

public class Log {

    private Long id;

    @CsvBindByPosition(position = 0)
    private String date;

    @CsvBindByPosition(position = 1)
    private String message;

    public Log(String date, String message) {
        this.date = date;
        this.message = message;
    }

    public Log(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}