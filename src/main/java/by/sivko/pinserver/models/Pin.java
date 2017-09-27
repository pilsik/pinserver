package by.sivko.pinserver.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "pins")
@NamedQueries({
        @NamedQuery(name = "Pin.getAllPins", query = "SELECT p FROM Pin p"),
        @NamedQuery(name = "Pin.removeExpiredPins", query = "DELETE FROM Pin p WHERE p.timestamp < current_timestamp"),
        @NamedQuery(name = "Pin.getPinByApiToken", query = "SELECT p FROM Pin p WHERE p.api_token=:api_token_value")
})
public class Pin implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int MIN = 1000;
    private static final int MAX = 9999;
    private static final int FIVE_MINUTES = 5*60*1000;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pin_gen")
    @SequenceGenerator(name = "pin_gen", sequenceName = "pins_seq")
    private Long id;

    private String email = "";

    private int code = 0;

    private Long operation_id = 0L;

    private String api_token = "";

    private Timestamp timestamp;

    public Pin() {
    }

    public Pin(String email, Long operation_id, String api_token) {
        this.email = email;
        this.operation_id = operation_id;
        this.api_token = api_token;
        this.code = MIN + (int) (Math.random() * ((MAX - MIN) + 1));
        this.timestamp = new Timestamp(System.currentTimeMillis()+FIVE_MINUTES);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Long getOperation_id() {
        return operation_id;
    }

    public void setOperation_id(Long operation_id) {
        this.operation_id = operation_id;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }
}
