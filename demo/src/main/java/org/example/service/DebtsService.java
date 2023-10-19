package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.DebtsRequest;
import org.example.excecao.NoItemException;
import org.example.model.Card;
import org.example.model.Debts;
import org.example.repository.CardRepository;
import org.example.repository.DebtsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DebtsService {
                private final CardRepository cardRepository;
                private final DebtsRepository debtsRepository;
        public Debts save (DebtsRequest debtsRequest){
                Card card = cardRepository.findById(debtsRequest.getIdCard()).orElseThrow(()->
                        new NoItemException("Cartão não encontrado"));

                Debts  debtsRequest1 = new Debts();
                debtsRequest1.setIdCard(card);
                debtsRequest1.setDivida(debtsRequest.getDividas());
                return debtsRepository.save(debtsRequest1);
        }

}
