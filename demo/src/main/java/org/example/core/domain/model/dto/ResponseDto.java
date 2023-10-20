package org.example.core.domain.model.dto;


import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
public class ResponseDto {
    @Getter
    private List<String> messages;
    public ResponseDto (String message) {
        this.messages = Arrays.asList(message);
    }

    public ResponseDto(List<String> messgess) {
        this.messages = messgess;
    }
}
