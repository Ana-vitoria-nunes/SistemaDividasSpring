package org.example.controller;

import com.example.demo.dto.DebtsRequest;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.DebtsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/divida")
@RequiredArgsConstructor
public class DebtsController {
    private final  DebtsService debtsService;
    @PostMapping
    public ResponseEntity saveDebts(@Valid @RequestBody DebtsRequest debtsRequest){
        try {
            debtsService.save(debtsRequest);
            return new ResponseEntity<>(new ResponseDto("Salvo!"), HttpStatus.CREATED);
        }catch (Exception erro){
            return new ResponseEntity<>(new ResponseDto(erro.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
