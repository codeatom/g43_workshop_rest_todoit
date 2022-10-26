package se.lexicon.todo_it_api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;


@Getter
@Setter
public class APIError {

    private Integer statusCode;

    private String statusValue;

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    private Map<String, String> errors;


    public APIError() {
        this.timestamp = LocalDateTime.now();
    }

    public APIError(Integer statusCode, String statusValue) {
        this();
        this.statusCode = statusCode;
        this.statusValue = statusValue;
    }

    public APIError(Integer statusCode, String statusText, String message) {
        this(statusCode, statusText);
        this.message = message;
    }

    public APIError(Integer statusCode, String statusValue, String message, Map<String, String> errors) {
        this(statusCode, statusValue, message);
        this.errors = errors;
    }

}
