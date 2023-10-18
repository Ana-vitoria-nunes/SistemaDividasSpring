package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.DebstRequestPayment;
import org.example.dto.PaymentDto;
import org.example.dto.ResponseDto;
import org.example.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamento")
@RequiredArgsConstructor
public class PaymentController {
   private final PaymentService paymentService;

   @PostMapping
    public ResponseEntity save (@RequestBody DebstRequestPayment debstRequestPayment, PaymentDto paymentDto ){
       try {
           paymentService.insertValueAcordingToDebts(debstRequestPayment,paymentDto);
           return new ResponseEntity<>(new ResponseDto("Parabens você efetuou seu pagamento!!"), HttpStatus.CREATED);
       }catch (Exception erro){
           return new ResponseEntity<>(new ResponseDto(erro.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
   // adicionar o data e o horário para a proxima spring
}
