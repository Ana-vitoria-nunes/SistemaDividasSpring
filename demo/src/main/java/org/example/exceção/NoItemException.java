package org.example.exceção;

public class NoItemException extends RuntimeException {
    public NoItemException (String menssage){
        super(menssage);
    }
}
