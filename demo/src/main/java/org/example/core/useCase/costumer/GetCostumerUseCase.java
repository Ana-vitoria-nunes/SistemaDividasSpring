package org.example.core.useCase.costumer;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.Card;
import org.example.core.domain.model.Costumer;
import org.example.core.domain.model.Payment;
import org.example.core.domain.model.dto.requestDto.AddressRequest;
import org.example.core.domain.model.dto.requestInfoDto.AddressRequestInfoDto;
import org.example.core.domain.model.dto.requestInfoDto.CostumerInfoDto;
import org.example.core.domain.model.dto.requestInfoDto.PaymentInfoDto;
import org.example.core.port.CardRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class GetCostumerUseCase {

   private final CardRepository cardRepository ;
    public List<CostumerInfoDto> findAll(){
        return cardRepository.findAll().stream().map(costumer -> putdata(costumer)).collect(Collectors.toList());
    }

    public CostumerInfoDto putdata(Card card){
        return CostumerInfoDto.builder()
                .idInterno(card.getIdCard())
                .nome(card.getCostumer().getName())
                .email(card.getCostumer().getEmail())
                .endereço(getAddressInfo(card.getCostumer()))
                .transações(getPayment(card.getIdPayments())).build();
    }

    public List<PaymentInfoDto> getPayment(List<Payment> payments){

        if(CollectionUtils.isEmpty(payments)){
            return Collections.emptyList();
        }
        return payments.stream().map(
                pay -> PaymentInfoDto.builder()
                        .externalId(pay.getExternalIdCard().getExternalIdCard())
                        .dayDebts(pay.getDayDebts())
                        .parcelas(pay.getQuota())
                        .totalLending(pay.getTotalLending())
                        .totalQuote(pay.getTotalQuota())
                        .stats(pay.getStats())
                        .build()

        ).collect(Collectors.toList());
    }


    public AddressRequestInfoDto getAddressInfo(@NotNull Costumer costumer){
        return AddressRequestInfoDto.builder()
                        .ruaAvenida(costumer.getAddress().getStreet())
                        .bairro(costumer.getAddress().getNeighborhood())
                        .numeroCasa(costumer.getAddress().getNumberHouse())
                        .estado(costumer.getAddress().getState())
                        .cidade(costumer.getAddress().getCity())
                        .cep(costumer.getAddress().getCep())
                        .build();

    }

}
