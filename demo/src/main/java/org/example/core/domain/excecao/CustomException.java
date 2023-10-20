package org.example.core.domain.excecao;

public class CustomException extends RuntimeException{
    public CustomException(String menssage){
        super(menssage);
    }
}