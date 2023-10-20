package org.example.core.domain.excecao;

public class NoItemException extends RuntimeException {
    public NoItemException (String menssage){
        super(menssage);
    }
}
