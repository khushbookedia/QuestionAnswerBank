package com.neosoft.QuestionAnswerBank.Exception.handler;

import com.neosoft.QuestionAnswerBank.Exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity handleResourceNotFoundException(ResourceNotFoundException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message",ex.getLocalizedMessage());
        body.put("statusCode",ex.getHttpStatus().value());

        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }
}
