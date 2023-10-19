package com.example.demo.service;

import org.example.dto.DebtsRequest;
import org.example.model.Card;
import org.example.model.Debts;
import org.example.repository.CardRepository;
import org.example.repository.DebtsRepository;
import org.example.service.DebtsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class DebtsServiceTest {

    @Mock
    private CardRepository cardRepository;
    @Mock
    private DebtsRepository debtsRepository;

    @InjectMocks
    private DebtsService service;

    @Test
    @DisplayName("Should return a new debts creat at data base")
    void save() {
         Card card = new Card();
         card.setId(1l);

         when(cardRepository.findById(1L)).thenReturn(Optional.of(card));

         DebtsRequest dtoDebts = new DebtsRequest(new BigDecimal(10.000),1L);

        Debts newDeb = new Debts();
        when(debtsRepository.save(any(Debts.class))).thenReturn(newDeb);


        Debts saved = service.save(dtoDebts);
        Assertions.assertEquals(saved,newDeb);
    }
}