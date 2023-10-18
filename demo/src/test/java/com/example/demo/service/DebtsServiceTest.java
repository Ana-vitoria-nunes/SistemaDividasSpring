package com.example.demo.service;

import com.example.demo.DebtsRequest;
import com.example.demo.model.Card;
import com.example.demo.model.Debts;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.DebtsRepository;
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
    private  CardRepository cardRepository;
    @Mock
    private  DebtsRepository debtsRepository;

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

//        newDeb.setIdCard(card);
//        newDeb.setDivida(dtoDebts.getDividas());

        Debts saved = service.save(dtoDebts);
        Assertions.assertEquals(saved,newDeb);
    }
}