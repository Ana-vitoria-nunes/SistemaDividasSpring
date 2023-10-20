package org.example.adapters.web;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.excecao.ApplicationAdviceController;
import org.example.core.useCase.costumer.SaveCostumerUseCase;
import org.example.core.domain.model.dto.ClienteRequest;
import org.example.core.domain.model.dto.ResponseDto;
import org.example.core.useCase.costumer.UpdateCostumerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController()
@RequestMapping("/cadastro")
@RequiredArgsConstructor
public class CostumerController {
   private final SaveCostumerUseCase saveCostumer;
   private final UpdateCostumerUseCase updateCostumer;
    //tratar excesão do erro de cpf
    // tratar exesão que senha pode ser repetida

    @PostMapping @Transactional()
    public ResponseEntity addUser(@Valid @RequestBody ClienteRequest cliente) {
        try {
            saveCostumer.saveCostumer(cliente);
            return new ResponseEntity<>(new ResponseDto("Cliente cadastrado com sucesso!!"), HttpStatus.CREATED);
        } catch (Exception erro){
            return new ResponseEntity<>(erro.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{idCostumer}/{escolha}")
    public ResponseEntity updateCostumer(@PathVariable(value = "idCostumer")String id,@RequestBody ClienteRequest clienteRequest,@PathVariable(value = "escolha") String escolha){
        try {
            return new ResponseEntity<>(updateCostumer.updateCostumer(clienteRequest,id,escolha),HttpStatus.OK);
        }catch (Exception erro){
            return new ResponseEntity<>(new ResponseDto(erro.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
