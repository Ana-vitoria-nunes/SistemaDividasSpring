package org.example.adapters.web;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.example.core.domain.model.dto.CardRequest;
import org.example.core.domain.model.dto.ResponseDto;
import org.example.core.useCase.card.SaveCardUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    SaveCardUseCase saveCardUseCase;

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CardRequest cardRequest){
        try {
            saveCardUseCase.saveCard(cardRequest);
            return new ResponseEntity<>(new ResponseDto("Cartão cadastrado com sucesso!!"), HttpStatus.CREATED);
        }catch (ConstraintViolationException exception){
            Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
            List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseDto(errorMessages), HttpStatus.BAD_REQUEST);
        }catch (DataIntegrityViolationException erro) {
            if (erro.getMessage().contains(" Detalhe: Key (id_externo_cliente)")){
                return new ResponseEntity<>(new ResponseDto("O Cliente ja foi cadastrado "),HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseDto("Verifique sua entrada de dados"), HttpStatus.BAD_REQUEST);
        } catch (Exception erro){
            return new ResponseEntity<>(new ResponseDto(erro.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
