package org.example.exceção;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.example.dto.ResponseDto;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Transactional
public class ApplicationAdviceController{

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handlerConstraintViolationException(ConstraintViolationException ex){
        return new ResponseDto(ex.getMessage());
    }

    @ExceptionHandler(DecodingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handlerHttpMessageNotReadableException(DecodingException exception){
        return new ResponseDto("Formato iválido");
    }
    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handlerWebExchangeBindException(WebExchangeBindException exception){
        return new ResponseDto(exception.getMessage());
    }
    @ExceptionHandler(UniqueIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto handlerCostumerUniqueIdException(UniqueIdException exception){
        return new ResponseDto(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handlerValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errors = fieldErrors
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

            return new ResponseDto("Erro validação"+errors);
        }

    }


