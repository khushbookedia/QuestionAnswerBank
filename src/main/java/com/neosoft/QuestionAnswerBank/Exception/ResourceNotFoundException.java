package com.neosoft.QuestionAnswerBank.Exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException{

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ResourceNotFoundException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
