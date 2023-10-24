package com.example.demo.card;
import org.example.core.domain.model.Card;
import org.example.core.domain.model.Costumer;
import org.example.core.domain.model.dto.requestDto.CardRequest;
import org.example.core.port.CardRepository;
import org.example.core.port.CostumerRepository;
import org.example.core.useCase.card.SaveCardUseCase;
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
class SaveCardUseCaseTest {

    @Mock
    private  CardRepository cardRepository;
    @Mock
    private  CostumerRepository costumerRepository;
    @InjectMocks
    private SaveCardUseCase cardUseCase;

    @Test
    @DisplayName("Method to create a new card and should return card created")
    void saveCard() {

        Costumer costumer = new Costumer();
        costumer.setExternalId("19j");

        String data = "05/2024";


        when(costumerRepository.findByExternalId("19j")).thenReturn(Optional.of(costumer));
        CardRequest cardRequest = new CardRequest("19j","Ana Nunes","1234567899874561","456",data, BigDecimal.valueOf(1500));

        cardUseCase.dataParseToFormat(cardRequest.getDataDeValidade());

        Card card = new Card();
        card.setExpiryDate(data);

        cardUseCase.dataParseToFormat(card.getExpiryDate());
        when(cardRepository.save(any(Card.class))).thenReturn(card);


        Card saved = cardUseCase.saveCard(cardRequest);
        Assertions.assertEquals(saved,card);
    }

}