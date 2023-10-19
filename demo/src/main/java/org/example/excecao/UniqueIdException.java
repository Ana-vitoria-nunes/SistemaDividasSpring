package org.example.excecao;

import lombok.Data;

@Data
public class UniqueIdException extends RuntimeException {
    public UniqueIdException(String message) {
        super(message);
    }
}
