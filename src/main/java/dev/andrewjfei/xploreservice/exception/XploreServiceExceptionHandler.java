package dev.andrewjfei.xploreservice.exception;

import dev.andrewjfei.xploreservice.transaction.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static dev.andrewjfei.xploreservice.enumeration.Error.UNEXPECTED_ERROR;
import static dev.andrewjfei.xploreservice.util.ObjectMapper.toResponse;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class XploreServiceExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(XploreServiceExceptionHandler .class);

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        if (exception instanceof XploreServiceException) {
            XploreServiceException xploreServiceException = (XploreServiceException) exception;
            return new ResponseEntity<>(toResponse(xploreServiceException.getError()), xploreServiceException.getHttpStatus());
        } else {
            logger.error(exception.getMessage());
            return new ResponseEntity<>(toResponse(UNEXPECTED_ERROR), INTERNAL_SERVER_ERROR);
        }
    }
}
