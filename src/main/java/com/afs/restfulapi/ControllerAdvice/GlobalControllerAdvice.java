package com.afs.restfulapi.ControllerAdvice;

import com.afs.restfulapi.Exception.CompanyNotFoundException;
import com.afs.restfulapi.Exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler({CompanyNotFoundException.class, EmployeeNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundExceptionHandler(Exception exception) {
        return new ErrorResponse(404, exception.getMessage());
    }
}
