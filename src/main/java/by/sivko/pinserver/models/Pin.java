package by.sivko.pinserver.models;

import javax.persistence.*;

@Entity
@Table(name = "pins")
public class Pin {

    private static final int MIN = 1000;
    private static final int MAX = 9999;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email = "";

    private int code = 0;

    private Long operation_id = 0L;

    private String api_token = "";

    public Pin() {
    }

    public Pin(String email, Long operation_id, String api_token) {
        this.email = email;
        this.operation_id = operation_id;
        this.api_token = api_token;
        this.code = MIN + (int) (Math.random() * ((MAX - MIN) + 1));
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
