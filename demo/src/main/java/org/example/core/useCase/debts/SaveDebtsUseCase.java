package org.example.core.useCase.debts;

import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.DebtsRequest;
import org.example.core.domain.excecao.NoItemException;
import org.example.core.domain.model.Card;
import org.example.core.domain.model.Debts;
import org.example.core.port.CardRepository;
import org.example.core.port.DebtsRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@RequiredArgsConstructor
public class SaveDebtsUseCase {
                private final CardRepository cardRepository;
                private final DebtsRepository debtsRepository;
        public Debts save (DebtsRequest debtsRequest){
                Card card = cardRepository.findByExternalIdCard(debtsRequest.getIdexternoCard()).orElseThrow(()->
                        new NoItemException("Cartão não encontrado"));

                Debts  debtsRequest1 = new Debts();
                debtsRequest1.generateAndSetExternalIdDebts();
                debtsRequest1.setExternalIdCard(card);
                debtsRequest1.setDivida(debtsRequest.getDividas());
                return debtsRepository.save(debtsRequest1);
        }

}
