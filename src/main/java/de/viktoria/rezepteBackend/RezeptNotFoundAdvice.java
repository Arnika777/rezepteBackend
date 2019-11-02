package de.viktoria.rezepteBackend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class RezeptNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RezeptNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(RezeptNotFoundException ex) {
        return ex.getMessage();
    }
}
