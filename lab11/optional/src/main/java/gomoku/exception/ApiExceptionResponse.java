package gomoku.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiExceptionResponse {

    public final String message;
    public final HttpStatus httpStatus;
    public final ZonedDateTime zonedDateTime;

    public ApiExceptionResponse(ApiException e, HttpStatus httpStatus) {
        this.message = e.getMessage();
        this.httpStatus = httpStatus;
        this.zonedDateTime = ZonedDateTime.now();
    }
}
