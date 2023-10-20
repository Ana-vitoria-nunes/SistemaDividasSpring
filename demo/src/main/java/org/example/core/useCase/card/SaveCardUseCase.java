package org.example.core.useCase.card;


import lombok.RequiredArgsConstructor;
import org.example.adapters.config.Pass;
import org.example.core.domain.model.dto.CardRequest;
import org.example.core.domain.excecao.NoItemException;
import org.example.core.domain.model.Card;
import org.example.core.domain.model.Costumer;
import org.example.core.port.CardRepository;
import org.example.core.port.CostumerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class SaveCardUseCase {
    private final CardRepository cardRepository;
    private final CostumerRepository costumerRepository;
    private final ModelMapper mapper;

    // colocar um método para confirmar dados
    public Card saveCard(CardRequest cardRequest) {
        Card newCard = new Card();

        String idExterno = cardRequest.getIdexternoCliente();
        Costumer costumer = costumerRepository.findByExternalId(idExterno).orElseThrow(() -> new NoItemException("Cliente não encontrado"));

        newCard.generateAndSetExternalIdCartao();
        newCard.setCostumer(costumer);
        newCard.setCostumer(costumer);
        String s = Pass.hashCVV(cardRequest.getCvv());
        String n = Pass.hashNumeroCartao(cardRequest.getNumeroCartao());
        newCard.setCvv(s);
        newCard.setNumeroCartao(n);
        dataParseToFormat(cardRequest.getDataDeValidade());
        newCard.setDataDevalidade(cardRequest.getDataDeValidade());
        newCard.setNomeClienteCartao(cardRequest.getNomeClienteCartao());
        newCard.setLimiteCartao(cardRequest.getLimiteCartao());


        return cardRepository.save(newCard);
    }
    public LocalDate dataParseToFormat(String dto) {
        DateTimeFormatter dateFormatOutput = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDate dataDeValidade;

      return dataDeValidade = YearMonth.parse(dto,dateFormatOutput).atDay(1);
    } // -> método em uma controladora
}
