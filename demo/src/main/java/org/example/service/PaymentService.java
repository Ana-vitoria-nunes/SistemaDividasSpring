package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.DebstRequestPayment;
import org.example.dto.PaymentDto;
import org.example.dto.ResponseDto;
import org.example.excecao.NoItemException;
import org.example.model.Card;
import org.example.model.Debts;
import org.example.model.Payment;
import org.example.model.Status;
import org.example.repository.CardRepository;
import org.example.repository.DebtsRepository;
import org.example.repository.PaymentRepository;
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
        // achar o id da divida OK
        // pegar a divida e calcular com o juros e as parcelas OK
        // inserir no banco de dados OK

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
        paymentMapDto.setIdCard(card);
        paymentMapDto.setValorTotalEmprestimo(paymentDto.getValorTotalEmprestimo());
        paymentMapDto.setValorTotalParcela(paymentDto.getValorTotalParcela());

        paymentMapDto.setDataHoraDivida(paymentMapDto.getDataHoraDivida());


        LocalDate dateConverted = requestPayment.getDatePaymentChoose();
        LocalDate dateToday = LocalDate.now();

        if (requestPayment.getDatePaymentChoose().getMonth() == dateToday.getMonth() && requestPayment.getDatePaymentChoose().getDayOfMonth() == dateToday.getDayOfMonth()) {
            paymentMapDto.setStatus(Status.CONCLUIDO);
            paymentMapDto.setDayMoth(requestPayment.getDatePaymentChoose());
        }else {
            paymentMapDto.setStatus(Status.PENDENTE);
            paymentMapDto.setDayMoth(requestPayment.getDatePaymentChoose());
        }

       return paymentRepository.save(paymentMapDto);
    }
//    public LocalDate convert (LocalDate data){
//            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            String dataFormatada = data.format(dateTimeFormatter);
//            return LocalDate.parse(dataFormatada, dateTimeFormatter);
//
//    }
}

