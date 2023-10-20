package org.example.adapters.web;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.core.useCase.address.SaveAddressUseCase;
import org.example.core.useCase.address.UpdateAddressUseCase;
import org.example.core.domain.model.dto.AddresRequest;
import org.example.core.domain.model.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class AddressController {

  private final SaveAddressUseCase saveAddressUseCase;
  private final UpdateAddressUseCase updateAddressUseCase;
  @PostMapping("/addingAddress")
  public ResponseEntity addAddress(@Valid @RequestBody AddresRequest addresRequest){
    try{
      saveAddressUseCase.saveAddress(addresRequest);
        return new ResponseEntity<>( new ResponseDto("Endereço criado"),HttpStatus.CREATED);
     }catch (ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        return new ResponseEntity<>(new ResponseDto(errorMessages), HttpStatus.BAD_REQUEST);
     }catch (Exception erro){
      return new ResponseEntity<>(erro.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{idAddress}")
  @Transactional()
  public ResponseEntity updateAddress(@Valid @PathVariable(value = "idAddress" ) String id, @Valid @RequestBody AddresRequest address){
    try {
          updateAddressUseCase.updateAllData(id,address);
         return new ResponseEntity<>(new ResponseDto("Atualizado"),HttpStatus.OK);
     }catch (Exception erro){
      return new ResponseEntity<>(erro.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
