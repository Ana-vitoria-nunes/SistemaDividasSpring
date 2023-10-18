package org.example.controller;

import com.example.demo.dto.ClienteRequest;
import com.example.demo.dto.ResponseDto;
import com.example.demo.exceção.ApplicationAdviceController;
import com.example.demo.exceção.UniqueIdException;
import com.example.demo.service.ClienteService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController()
@RequestMapping("/cadastro")
@RequiredArgsConstructor
public class CostumerController {
   private final ClienteService clienteService;
    //tratar excesão do erro de cpf
    // tratar exesão que senha pode ser repetida

    @PostMapping()
    public ResponseEntity addUser(@Valid @RequestBody ClienteRequest cliente) {
        try {
            clienteService.saveCostumer(cliente);
            return new ResponseEntity<>(new ResponseDto("Cliente cadastrado com sucesso!!"), HttpStatus.CREATED);
        }catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
            List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseDto(errorMessages), HttpStatus.BAD_REQUEST);
        } catch (Exception erro){
            return new ResponseEntity<>(erro.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{idCostumer}/{escolha}")
    public ResponseEntity updateCostumer(@PathVariable(value = "idCostumer")Long id,@RequestBody ClienteRequest clienteRequest,@PathVariable(value = "escolha") String escolha){
        try {
            return new ResponseEntity<>(clienteService.updateCostumer(clienteRequest,id,escolha),HttpStatus.OK);
        }catch (Exception erro){
            return new ResponseEntity<>(new ResponseDto(erro.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
