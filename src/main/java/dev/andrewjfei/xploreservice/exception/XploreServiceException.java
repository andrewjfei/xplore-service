package dev.andrewjfei.xploreservice.exception;

import dev.andrewjfei.xploreservice.enumeration.Error;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class XploreServiceException extends RuntimeException {

    private Error error;

    private HttpStatus httpStatus;

    public XploreServiceException() {

    }

    public XploreServiceException(Error error) {
        this.error = error;
    }

    public XploreServiceException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public XploreServiceException(Error error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }
}
