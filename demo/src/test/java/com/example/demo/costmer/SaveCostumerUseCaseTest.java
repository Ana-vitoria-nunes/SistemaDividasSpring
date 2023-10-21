package com.example.demo.costmer;

import org.example.adapters.config.Pass;
import org.example.core.domain.model.Costumer;
import org.example.core.domain.model.dto.CostumerRequest;
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
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class SaveCostumerUseCaseTest {
    @Mock
    private CostumerRepository costumerRepository;

    @InjectMocks
    SaveCostumerUseCase saveCostumerUseCase;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Method to create a new costumer and should return costumer created")
    void saveCostumer() {


        CostumerRequest dto = new CostumerRequest("ana", LocalDate.of(2004, 12, 20), "Ana@Gamil.com", "123", "81450167039", "3456787654");

        Costumer costumer = new Costumer();

        String s = Pass.hashPassword(dto.getSenha());

        dto.setSenha(s);
        costumer.setPassword(dto.getSenha());
        costumer.generateAndSetExternalId();
        costumer.setId_Cliente(1l);
        costumer.setPassword(dto.getSenha());
        costumer.setEmail(dto.getEmail());
        costumer.setCpf(dto.getCpf());
        costumer.setName(dto.getNomeCompleto());
        costumer.setBirthDate(dto.getDataNascimento());
        costumer.setPhone(dto.getTelefone());

        Costumer costumer1 = saveCostumerUseCase.saveCostumer(dto);

        Assertions.assertNotNull(costumer);
        Assertions.assertEquals(costumer,costumer1);
    }
    }