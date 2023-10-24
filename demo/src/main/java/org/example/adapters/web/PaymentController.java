package org.example.adapters.web;

import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.requestDto.DebstRequestPayment;
import org.example.core.domain.model.dto.requestDto.PaymentDto;
import org.example.core.domain.model.dto.responseDto.ResponseDto;
import org.example.core.useCase.payment.PaymentService;
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
