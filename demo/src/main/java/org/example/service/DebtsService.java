package org.example.service;

import com.example.demo.DebtsRequest;
import com.example.demo.exceção.NoItemException;
import com.example.demo.model.Card;
import com.example.demo.model.Debts;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.DebtsRepository;
import lombok.RequiredArgsConstructor;
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
