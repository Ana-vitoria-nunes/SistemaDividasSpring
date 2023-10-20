package org.example.adapters.web;
import jakarta.validation.Valid;
import org.example.core.domain.model.dto.CardRequest;
import org.example.core.domain.model.dto.ResponseDto;
import org.example.core.useCase.card.SaveCardUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adicionarCartão")
public class CardController {
    @Autowired
    SaveCardUseCase saveCardUseCase;

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CardRequest cardRequest){
        try {
            saveCardUseCase.saveCard(cardRequest);
            return new ResponseEntity<>(new ResponseDto("Cartão cadastrado com sucesso!!"), HttpStatus.CREATED);
        }catch (Exception erro){
            return new ResponseEntity<>(new ResponseDto(erro.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
