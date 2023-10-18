package org.example.controller;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.example.dto.CardRequest;
import org.example.dto.ResponseDto;
import org.example.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/adicionarCartão")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CardRequest cardRequest){
        try {
            cardService.saveCard(cardRequest);
            return new ResponseEntity<>(new ResponseDto("Cartão cadastrado com sucesso!!"), HttpStatus.CREATED);
        }catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
            List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseDto(errorMessages), HttpStatus.BAD_REQUEST);
        }catch (Exception erro){
            return new ResponseEntity<>(new ResponseDto(erro.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
