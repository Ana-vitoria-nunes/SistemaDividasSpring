package com.example.demo.payment;

import org.example.core.domain.model.Card;
import org.example.core.domain.model.Debts;
import org.example.core.domain.model.Payment;
import org.example.core.domain.model.dto.requestDto.DebstRequestPayment;
import org.example.core.domain.model.dto.requestDto.PaymentDto;
import org.example.core.port.CardRepository;
import org.example.core.port.DebtsRepository;
import org.example.core.port.PaymentRepository;
import org.example.core.useCase.payment.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private  PaymentRepository paymentRepository;
    @Mock
    private  DebtsRepository debtsRepository;
    @Mock
    private  CardRepository cardRepository;

    @InjectMocks
    PaymentService paymentService;
    @Test
    @DisplayName("Method to create a new card and should return card created")
    void saveCard() {

        Card card = new Card();
        card.setExternalIdCard("23p");

        Debts debts = new Debts();
        debts.setExternalIdDebts("13p");
        debts.setDebts(new BigDecimal(10.000));

        when(debtsRepository.findByExternalIdDebts("13p")).thenReturn(Optional.of(debts));
        when(cardRepository.findByExternalIdCard("23p")).thenReturn(Optional.of(card));

        DebstRequestPayment debstRequestPayment=new DebstRequestPayment(2,"13p","23p", LocalDate.now());
        PaymentDto paymentDto =new PaymentDto();


        Payment payment = new Payment();
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment saved = paymentService.insertValueAcordingToDebts(debstRequestPayment,paymentDto);
        Assertions.assertEquals(saved,payment);
    }


}