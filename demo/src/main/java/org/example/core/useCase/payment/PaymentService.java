package org.example.core.useCase.payment;

import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.DebstRequestPayment;
import org.example.core.domain.model.dto.PaymentDto;
import org.example.core.domain.model.dto.ResponseDto;
import org.example.core.domain.excecao.NoItemException;
import org.example.core.domain.model.Card;
import org.example.core.domain.model.Debts;
import org.example.core.domain.model.Payment;
import org.example.core.domain.model.Status;
import org.example.core.port.CardRepository;
import org.example.core.port.DebtsRepository;
import org.example.core.port.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentService {
      private final PaymentRepository paymentRepository;
      private final DebtsRepository debtsRepository;
      private final CardRepository cardRepository;
      private double juros = 0;
      private int parcela = 0;
    public Payment insertValueAcordingToDebts(DebstRequestPayment requestPayment, PaymentDto paymentDto) {

        Debts debts = debtsRepository.findByExternalIdDebts(requestPayment.getIdexternoDebts()).orElseThrow(() ->
                new NoItemException("Divida não encontrada"));

        Card card = cardRepository.findByExternalIdCard(requestPayment.getIdexternoCard()).orElseThrow(() ->
                new NoItemException("Cartão não encontrado"));


        Payment paymentMapDto = new Payment();

        switch (requestPayment.getEscolha()) {
            case 1 -> {
                juros = 0.05;
                parcela = 2;
            }
            case 2 -> {
                juros = 0.07; // Juros de 7% para 4 parcelas
                parcela = 4;
            }
            case 3 -> {
                juros = 0.09; // Juros de 9% para 6 parcelas
                parcela = 6;
            }
            case 4 -> {
                juros = 0.11; // Juros de 11% para 8 parcelas
                parcela = 8;
            }
            case 5 -> {
                juros = 0.13; // Juros de 13% para 10 parcelas
                parcela = 10;
            }
            case 6 -> {
                juros = 0.15; // Juros de 15% para 12 parcelas
                parcela = 12;

            }
            default -> new ResponseDto("Opção inválida, tente novamente");
        }
        BigDecimal valorTotalEmprestimo = debts.getDivida().multiply(BigDecimal.ONE.add(BigDecimal.valueOf(juros)));
        BigDecimal valorTotalParcela = valorTotalEmprestimo.divide(BigDecimal.valueOf(parcela), 2, BigDecimal.ROUND_HALF_UP);

        paymentMapDto.generateAndSetExternalIdPayment();
        paymentDto.setValorTotalParcela(valorTotalParcela);
        paymentDto.setValorTotalEmprestimo(valorTotalEmprestimo);
        paymentMapDto.setExternalIdCard(card);
        paymentMapDto.setValorTotalEmprestimo(paymentDto.getValorTotalEmprestimo());
        paymentMapDto.setValorTotalParcela(paymentDto.getValorTotalParcela());

        paymentMapDto.setDataHoraDivida(paymentMapDto.getDataHoraDivida());


        LocalDate dateToday = LocalDate.now();
        LocalDate datePaymentChoose = requestPayment.getDatePaymentChoose();

        if (dateToday.getMonthValue() != datePaymentChoose.getMonthValue()){
            paymentMapDto.setStatus(Status.PENDENTE);
        } else {
            paymentMapDto.setStatus(Status.CONCLUIDO);
        }
        paymentMapDto.setDayMoth(requestPayment.getDatePaymentChoose());
       return paymentRepository.save(paymentMapDto);
    }

}

