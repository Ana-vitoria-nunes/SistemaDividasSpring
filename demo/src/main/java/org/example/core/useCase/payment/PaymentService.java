package org.example.core.useCase.payment;

import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.requestDto.DebstRequestPayment;
import org.example.core.domain.model.dto.requestDto.PaymentDto;
import org.example.core.domain.model.dto.requestInfoDto.DebtsInfoDto;
import org.example.core.domain.model.dto.responseDto.ResponseDto;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
      private final PaymentRepository paymentRepository;
      private final DebtsRepository debtsRepository;
      private final CardRepository cardRepository;
      private double juros = 0;
      private int parcela = 0;
      // lembrar de adicionar a quantidade de parcelas
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
        BigDecimal valorTotalEmprestimo = debts.getDebts().multiply(BigDecimal.ONE.add(BigDecimal.valueOf(juros)));
        BigDecimal valorTotalParcela = valorTotalEmprestimo.divide(BigDecimal.valueOf(parcela), 2, BigDecimal.ROUND_HALF_UP);

        paymentMapDto.generateAndSetExternalIdPayment();
        paymentDto.setValorTotalParcela(valorTotalParcela);
        paymentDto.setValorTotalEmprestimo(valorTotalEmprestimo);


        paymentMapDto.setQuota(requestPayment.getEscolha());
        paymentMapDto.setExternalIdCard(card);
        paymentMapDto.setTotalLending(paymentDto.getValorTotalEmprestimo());
        paymentMapDto.setTotalQuota(paymentDto.getValorTotalParcela());
        paymentMapDto.setDayDebts(paymentMapDto.getDayDebts());

        paymentMapDto.setDebts(saveDebts(debts));

        LocalDate dateToday = LocalDate.now();
        LocalDate datePaymentChoose = requestPayment.getDatePaymentChoose();

        if (dateToday.getMonthValue() != datePaymentChoose.getMonthValue()){
            paymentMapDto.setStats(Status.PENDENTE);
        } else {
            paymentMapDto.setStats(Status.CONCLUIDO);
        }
        paymentMapDto.setDayMoth(requestPayment.getDatePaymentChoose());
       return paymentRepository.save(paymentMapDto);
    }


    public List<Debts> saveDebts(Debts debts) {
        Payment payment = paymentRepository.findByExternalIdPayment(String.valueOf(debts.getExternalIdPayment())).orElseThrow(()->
                new NoItemException("Pagamento não encontrado"));

        List<Debts> debtsList = payment.getDebts(); // Obtém a lista de dívidas associadas ao pagamento
        debtsList.add(debts); // Adiciona a nova dívida à lista
        payment.setDebts(debtsList); // Atualiza a lista de dívidas no pagamento

        paymentRepository.save(payment); // Salva o pagamento atualizado no repositório

        return debtsList;
    }

}

