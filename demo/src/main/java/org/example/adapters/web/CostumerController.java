package org.example.adapters.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.core.useCase.costumer.SaveCostumerUseCase;
import org.example.core.domain.model.dto.CostumerRequest;
import org.example.core.domain.model.dto.ResponseDto;
import org.example.core.useCase.costumer.UpdateCostumerUseCase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/cadastro")
@RequiredArgsConstructor
public class CostumerController {
   private final SaveCostumerUseCase saveCostumer;
   private final UpdateCostumerUseCase updateCostumer;
    //tratar excesão do erro de cpf
    // tratar exesão que senha pode ser repetida

    @PostMapping
    public ResponseEntity addUser(@Valid @RequestBody CostumerRequest cliente) {
        try {
            saveCostumer.saveCostumer(cliente);
            return new ResponseEntity<>(new ResponseDto("Cliente cadastrado com sucesso!!"), HttpStatus.CREATED);
        }catch (DataIntegrityViolationException erro) {
            if (erro.getMessage().contains("  Detalhe: Key (cpf)")){
                return new ResponseEntity<>(new ResponseDto("Cpf já existe"),HttpStatus.BAD_REQUEST);
            } else if (erro.getMessage().contains(" Detalhe: Key (email)")) {
                return new ResponseEntity<>(new ResponseDto("Email já existe"),HttpStatus.BAD_REQUEST);
            } else if (erro.getMessage().contains("Detalhe: Key (name)")) {
                return new ResponseEntity<>(new ResponseDto("Nome completo já existe"),HttpStatus.BAD_REQUEST);
            }else if (erro.getMessage().contains(" Detalhe: Key (phone)")) {
                return new ResponseEntity<>(new ResponseDto("Telefone já existe"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseDto("Verifique sua entrada de dados"), HttpStatus.BAD_REQUEST);
        } catch (Exception erro){
            return new ResponseEntity<>(erro.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{idCostumer}/{escolha}")
    public ResponseEntity updateCostumer(@PathVariable(value = "idCostumer")String id, @RequestBody CostumerRequest costumerRequest, @PathVariable(value = "escolha") String escolha){
        try {
            return new ResponseEntity<>(updateCostumer.updateCostumer(costumerRequest,id,escolha),HttpStatus.OK);
        }catch (Exception erro){
            return new ResponseEntity<>(new ResponseDto(erro.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
