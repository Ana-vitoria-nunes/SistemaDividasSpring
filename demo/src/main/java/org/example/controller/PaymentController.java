package org.example.controller;

import com.example.demo.dto.DebstRequestPayment;
import com.example.demo.dto.PaymentDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
