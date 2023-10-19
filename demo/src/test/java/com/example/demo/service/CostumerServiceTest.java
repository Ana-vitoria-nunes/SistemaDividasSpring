package com.example.demo.service;

import org.example.dto.ClienteRequest;
import org.example.model.Costumer;
import org.example.repository.CostumerRepository;
import org.example.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import static org.mockito.Mockito.*;

import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;


@ActiveProfiles("test")
class CostumerServiceTest {
    @Mock
    private CostumerRepository costumerRepository;

    @InjectMocks
    ClienteService clienteService;
    private ModelMapper mapper = new ModelMapper();

    @BeforeEach
    private void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @DisplayName("Method to create a new costumer and should return costumer created")
    void saveCostumer() {
        ClienteRequest dto = new ClienteRequest(1l,"ana",LocalDate.of( 2003,11,11),"Ana@Gamil.com","123A","81450167039","92159133");
        Costumer costumer = mapper.map(dto, Costumer.class);


        when(costumerRepository.save(any(Costumer.class))).thenReturn(costumer);
        Costumer costumerSaved = costumerRepository.save(costumer);

        Assertions.assertNotNull(costumerSaved);
    }


    @Test
    @DisplayName("Update email costumer´s that return their values modernize")
    void updateCostumerEmail(){
        ClienteRequest dto = new ClienteRequest(1l,"ana",LocalDate.of( 2003,11,11),"Ana@Gamil.com","123A","81450167039","92159133");
        Costumer costumer = mapper.map(dto, Costumer.class);

        String newEmail = "Ana@lala.com";
        dto.setEmail(newEmail);
        when(costumerRepository.findById(1L)).thenReturn(Optional.of(costumer));

        clienteService.updateCostumer(dto,1L,"Email");

        Assertions.assertNotNull(costumer);
        Assertions.assertEquals(newEmail,costumer.getEmail());

    }
    @Test
    @DisplayName("Update telephone costumer´s that return their values modernize")
    void updateCostumerPassword(){
        ClienteRequest dto = new ClienteRequest(1l,"ana",LocalDate.of( 2003,11,11),"Ana@Gamil.com","123A","81450167039","92159133");
        Costumer costumer = mapper.map(dto, Costumer.class);

        String newTelephone = "92658736";
        dto.setTelefone(newTelephone);

        when(costumerRepository.findById(1L)).thenReturn(Optional.of(costumer));

        clienteService.updateCostumer(dto,1L,"Telefone");

        Assertions.assertNotNull(costumer);
        Assertions.assertEquals(newTelephone,costumer.getTelefone());
    }



}