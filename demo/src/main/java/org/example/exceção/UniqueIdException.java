package org.example.exceção;

import lombok.Data;

@Data
public class UniqueIdException extends RuntimeException {
    public UniqueIdException(String message) {
        super(message);
    }
}
