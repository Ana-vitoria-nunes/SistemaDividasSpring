package com.example.demo.service;

import org.example.dto.AddresRequest;
import org.example.dto.ClienteRequest;
import org.example.model.Address;
import org.example.model.Costumer;
import org.example.model.Debts;
import org.example.repository.AddressRepository;
import org.example.repository.CostumerRepository;
import org.example.service.AddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AddressServiceTest {
//    @Mock
//    private  AddressRepository addressRepository;
//    @InjectMocks
//    private AddressService addressService;
//    @Mock
//    private  CostumerRepository costumerRepository;
//
//    @Test
//    @DisplayName("Method to create a new address and should return address created")
//    void salvarEndereço() {
//       Costumer costumer = new Costumer();
//       costumer.setId_Cliente(1l);
//
//        when(costumerRepository.findById(1l)).thenReturn(Optional.of(costumer));
//        AddresRequest addresRequest = new AddresRequest(1l,costumer.getId_Cliente(),"Muarama","Lavenir da cunha",12,"MG","Patos","12345-45");
//
//        Address address = new Address();
//
//        when(addressRepository.save(any(Address.class))).thenReturn(address);
//
//        Address saved = addressService.salvarEndereço(addresRequest);
//        Assertions.assertEquals(saved,address);
//    }
//
//    @Test
//    void updateAllData() {
//
//        Address address = new Address();
//        address.setId_Endereco(1l);
//
//        Costumer costumer = new Costumer();
//        costumer.setId_Cliente(1l);
//
//        when(costumerRepository.findById(1l)).thenReturn(Optional.of(costumer));
//        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
//
//        AddresRequest addresRequest = new AddresRequest(1l,costumer.getId_Cliente(),"Muarama","Lavenir da cunha",12,"MG","Patos","12345-45");
//        String newneighborhood = "Planalto";
//
//        addresRequest.setBairro(newneighborhood);
//        addressService.updateAllData(1l,addresRequest);
//
//
//        Assertions.assertEquals(newneighborhood,addresRequest.getBairro());
//
//    }
}