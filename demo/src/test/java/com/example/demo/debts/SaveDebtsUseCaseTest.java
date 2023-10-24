package com.example.demo.debts;
import org.example.core.domain.model.Card;
import org.example.core.domain.model.Debts;
import org.example.core.domain.model.dto.requestDto.DebtsRequest;
import org.example.core.port.CardRepository;
import org.example.core.port.DebtsRepository;
import org.example.core.useCase.debts.SaveDebtsUseCase;
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
class SaveDebtsUseCaseTest {

    @Mock
    private  CardRepository cardRepository;
    @Mock
    private  DebtsRepository debtsRepository;
    @InjectMocks
    private SaveDebtsUseCase debtsUseCase;

    @Test
    @DisplayName("Method to create a new debts and should return debts created")
    void saveCard() {

        Card card = new Card();
        card.setExternalIdCard("29p");

        when(cardRepository.findByExternalIdCard("29p")).thenReturn(Optional.of(card));
        DebtsRequest debtsRequest = new DebtsRequest(BigDecimal.valueOf(10.000),"29p");

        Debts debts = new Debts();
        when(debtsRepository.save(any(Debts.class))).thenReturn(debts);

        Debts saved = debtsUseCase.save(debtsRequest);
        Assertions.assertEquals(saved,debts);
    }


}