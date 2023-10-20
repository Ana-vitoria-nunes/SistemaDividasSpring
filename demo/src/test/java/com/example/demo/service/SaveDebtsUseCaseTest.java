package com.example.demo.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class SaveDebtsUseCaseTest {

//    @Mock
//    private CardRepository cardRepository;
//    @Mock
//    private DebtsRepository debtsRepository;
//
//    @InjectMocks
//    private DebtsService service;
//
//    @Test
//    @DisplayName("Should return a new debts creat at data base")
//    void save() {
//         Card card = new Card();
//         card.setId(1l);
//
//         when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
//
//         DebtsRequest dtoDebts = new DebtsRequest(new BigDecimal(10.000),1L);
//
//        Debts newDeb = new Debts();
//        when(debtsRepository.save(any(Debts.class))).thenReturn(newDeb);
//
//
//        Debts saved = service.save(dtoDebts);
//        Assertions.assertEquals(saved,newDeb);
//    }
}