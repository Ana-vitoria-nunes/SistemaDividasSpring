package org.example.exceção;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UniqueIdException extends RuntimeException {
    public UniqueIdException(String message) {
        super(message);
    }
}
