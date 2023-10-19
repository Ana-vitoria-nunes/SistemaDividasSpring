package org.example.excecao;

public class NoItemException extends RuntimeException {
    public NoItemException (String menssage){
        super(menssage);
    }
}
