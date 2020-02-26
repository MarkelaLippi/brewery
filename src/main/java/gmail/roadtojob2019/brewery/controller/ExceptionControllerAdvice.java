package gmail.roadtojob2019.brewery.controller;

import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerNotFoundException;
import gmail.roadtojob2019.brewery.exception.BrewerySuchCustomerAlreadyExistException;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Level;

@ControllerAdvice
@Log
public class ExceptionControllerAdvice {

    @ExceptionHandler(
            {BrewerySuchCustomerAlreadyExistException.class, BrewerySuchCustomerNotFoundException.class})
    private ResponseEntity<ErrorMessage> handleBadRequest(final Exception e) {
        log.log(Level.SEVERE, e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Data
    public static class ErrorMessage {

        private final String errorMessage;
    }
}
