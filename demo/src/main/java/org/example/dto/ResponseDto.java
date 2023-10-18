package org.example.dto;

import jakarta.transaction.Transactional;
import lombok.Getter;

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
