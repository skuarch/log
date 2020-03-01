package app.structure.logreader.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "logs")
public class Log {

    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "log_date")
    private String date;

    @Column(name = "log_message")
    private String message;

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
