package org.example.service;

import com.example.demo.CardRequest;
import com.example.demo.exceção.NoItemException;
import com.example.demo.model.Card;
import com.example.demo.model.Costumer;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.CostumerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CostumerRepository costumerRepository;
    private final ModelMapper mapper;

    // colocar um método para confirmar dados
    public Card saveCard ( CardRequest cardRequest){
        Card newCard = new Card();
        Costumer costumer = costumerRepository.findById(cardRequest.getIdCostumer()).orElseThrow(
                () -> new NoItemException("Cliente não encontrado")
        );
        newCard.setCostumer(costumer);
        newCard.setCvv(cardRequest.getCvv());
       // newCard.setPagamento(cardRequest.get);
        newCard.setDataDevalidade(cardRequest.getDataDeValidade());
        newCard.setNomeClienteCartao(cardRequest.getNomeClienteCartao());
        newCard.setLimiteCartao(cardRequest.getLimiteCartao());
        newCard.setNumeroCartao(cardRequest.getNumeroCartao());

       return cardRepository.save(newCard);
    }




}
