package com.example.demo.service;

import org.example.core.domain.model.dto.AddresRequest;
import org.example.core.domain.model.Address;
import org.example.core.domain.model.Costumer;
import org.example.core.port.AddressRepository;
import org.example.core.port.CostumerRepository;
import org.example.service.AddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AddressServiceTest {
    @Mock
    private  AddressRepository addressRepository;
//    @Mock
//    private AddressRepository repository;
    @InjectMocks
    private AddressService addressService;
    @Mock
    private  CostumerRepository costumerRepository;

    @Test
    @DisplayName("Method to create a new address and should return address created")
    void salvarEndereço() {
       Costumer costumer = new Costumer();
       costumer.setExternalId("12A");

        when(costumerRepository.findByExternalId("12A")).thenReturn(Optional.of(costumer));
        AddresRequest addresRequest = new AddresRequest(costumer.getExternalId(),"Muarama","Lavenir da cunha",12,"MG","Patos","12345-45");
        Address address = new Address();

        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address saved = addressService.salvarEndereço(addresRequest);
        Assertions.assertEquals(saved,address);
    }

//    @Test
//    void updateAllData() {
//
//        Address address = new Address();
//        address.setExternalIdAddress(1l);
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