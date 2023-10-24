package com.example.demo.costmer;

import org.example.core.domain.model.Costumer;
import org.example.core.domain.model.dto.requestDto.CostumerRequest;
import org.example.core.port.CostumerRepository;
import org.example.core.useCase.costumer.UpdateCostumerUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UpdateCostumerUseCaseTest {

    @Mock
    private  CostumerRepository costumerRepository;

    @InjectMocks
    private UpdateCostumerUseCase updateCostumerUseCase;

    @Test
    void updateAllData() {
        Costumer costumer = new Costumer();
        costumer.setExternalId("19j");

        when(costumerRepository.findByExternalId(costumer.getExternalId())).thenReturn(Optional.of(costumer));

        CostumerRequest costumerRequest = new CostumerRequest("ana", LocalDate.of( 2003,11,11),"Ana@Gamil.com","123A","81450167039","92159133");

        String email = "AnaFlavia@Gamil.com";

        costumerRequest.setEmail(email);
        updateCostumerUseCase.updateCostumer(costumerRequest,"19j","2");


        Assertions.assertEquals(email, costumerRequest.getEmail());

    }

}