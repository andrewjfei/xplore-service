package dev.andrewjfei.xploreservice.exception;

import dev.andrewjfei.xploreservice.enumeration.Error;
import org.springframework.http.HttpStatus;

import static dev.andrewjfei.xploreservice.enumeration.Error.UNSUCCESSFUL_AUTHENTICATION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class AuthenticationException extends XploreServiceException {

    private final Error defaultError = UNSUCCESSFUL_AUTHENTICATION;

    private final HttpStatus defaultHttpStatus = BAD_REQUEST;

    public AuthenticationException() {
        super();
        setError(defaultError);
        setHttpStatus(defaultHttpStatus);
    }

    public AuthenticationException(Error error) {
        super(error);
        setHttpStatus(defaultHttpStatus);
    }

    public AuthenticationException(HttpStatus httpStatus) {
        super(httpStatus);
        setError(defaultError);
    }

    public AuthenticationException(Error error, HttpStatus httpStatus) {
        super(error, httpStatus);
    }
}
