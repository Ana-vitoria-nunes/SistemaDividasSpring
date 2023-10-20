package org.example.core.domain.excecao;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.example.core.domain.model.dto.ResponseDto;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationAdviceController{

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handlerConstraintViolationException(ConstraintViolationException ex){
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        return new ResponseDto(errorMessages);
    }

    @ExceptionHandler(DecodingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handlerHttpMessageNotReadableException(DecodingException exception){
        return new ResponseDto(exception.getMessage());
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handlerWebExchangeBindException(WebExchangeBindException exception){
        return new ResponseDto(exception.getMessage());
    }



}


