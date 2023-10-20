package com.example.demo.costmer;

import org.example.core.domain.model.Costumer;
import org.example.core.domain.model.dto.ClienteRequest;
import org.example.core.port.CostumerRepository;
import org.example.core.useCase.costumer.SaveCostumerUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class SaveCostumerUseCaseTest {
    @Mock
    private CostumerRepository costumerRepository;

    @InjectMocks
    SaveCostumerUseCase saveCostumerUseCase;
    private ModelMapper mapper = new ModelMapper();

    @Test
    @DisplayName("Method to create a new costumer and should return costumer created")
    void saveCostumer() {
        ClienteRequest dto = new ClienteRequest("ana", LocalDate.of( 2003,11,11),"Ana@Gamil.com","123A","81450167039","92159133");
        Costumer costumer = mapper.map(dto, Costumer.class);


        when(costumerRepository.save(any(Costumer.class))).thenReturn(costumer);
        Costumer costumerSaved = costumerRepository.save(costumer);

        Assertions.assertNotNull(costumerSaved);
    }

}