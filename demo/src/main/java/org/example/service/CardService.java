package org.example.service;


import lombok.RequiredArgsConstructor;
import org.example.dto.CardRequest;
import org.example.excecao.NoItemException;
import org.example.model.Card;
import org.example.model.Costumer;
import org.example.repository.CardRepository;
import org.example.repository.CostumerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CostumerRepository costumerRepository;
    private final ModelMapper mapper;

    // colocar um método para confirmar dados
    public Card saveCard (CardRequest cardRequest){
        Card newCard = new Card();

        String idExterno = cardRequest.getIdexternoCliente();
        Costumer costumer = costumerRepository.findByExternalId(idExterno).orElseThrow(() -> new NoItemException("Cliente não encontrado"));
        newCard.setCostumer(costumer);
       // newCard.setPagamento(cardRequest.get);

//        newCard.setCostumer(costumer);
        newCard.setDataDevalidade(cardRequest.getDataDeValidade());
        newCard.setNomeClienteCartao(cardRequest.getNomeClienteCartao());
        newCard.setCvv(cardRequest.getCvv());
        newCard.setLimiteCartao(cardRequest.getLimiteCartao());
        newCard.setNumeroCartao(cardRequest.getNumeroCartao());

       return cardRepository.save(newCard);
    }




}
