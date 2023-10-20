package org.example.adapters.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.DebtsRequest;
import org.example.core.domain.model.dto.ResponseDto;
import org.example.core.useCase.debts.SaveDebtsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/divida")
@RequiredArgsConstructor
public class DebtsController {
    private final SaveDebtsUseCase saveDebtsUseCase;
    @PostMapping
    public ResponseEntity saveDebts(@Valid @RequestBody DebtsRequest debtsRequest){
        try {
            saveDebtsUseCase.save(debtsRequest);
            return new ResponseEntity<>(new ResponseDto("Salvo!"), HttpStatus.CREATED);
        }catch (Exception erro){
            return new ResponseEntity<>(new ResponseDto(erro.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
