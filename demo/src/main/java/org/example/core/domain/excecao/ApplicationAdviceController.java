package org.example.core.domain.excecao;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.ResponseDto;
import org.springframework.core.codec.DecodingException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationAdviceController extends ResponseEntityExceptionHandler {

    //private final EnderecoValidator enderecoValidator;
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



    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handlerDuplicateKeyException(DuplicateKeyException erro){
        return new ResponseDto(erro.getMessage());
    }

    @ExceptionHandler(UnexpectedRollbackException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto handleUnexpectedRollbackException(UnexpectedRollbackException ex) {
        return new ResponseDto(ex.getMessage());
    }


}


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException erro){
//        return new ErrorResponse(erro.getBindingResult());
//    }


//    @ExceptionHandler(WebExchangeBindException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseDto handlerWebExchangeBindException(WebExchangeBindException exception){
//        return new ResponseDto(exception.getMessage());
//    }





