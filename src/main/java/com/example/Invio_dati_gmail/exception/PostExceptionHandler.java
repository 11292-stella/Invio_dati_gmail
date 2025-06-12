package com.example.Invio_dati_gmail.exception;


import com.example.Invio_dati_gmail.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class PostExceptionHandler {

    @ExceptionHandler(NonTrovatoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError nonTrovatoExceptionHandler(NonTrovatoException e){
        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        apiError.setDataErrore(LocalDateTime.now());

        return apiError;
    }

    @ExceptionHandler(ValidationException.class)//serve per mappare il metodo

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError ValidationExceptionHandler(ValidationException e){

        ApiError error = new ApiError();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        return error;

    }
}
