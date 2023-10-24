package com.example.demo.address;

import org.example.core.domain.model.Address;
import org.example.core.domain.model.Costumer;
import org.example.core.domain.model.dto.requestDto.AddressRequest;
import org.example.core.port.AddressRepository;
import org.example.core.port.CostumerRepository;
import org.example.core.useCase.address.UpdateAddressUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UpdateAddressUseCaseTest {

    @Mock
    private AddressRepository addressRepository;
    @InjectMocks
    private UpdateAddressUseCase updateAddress;
    @Mock
    private CostumerRepository costumerRepository;

    @Test
    void updateAllData() {

        Address address = new Address();
        address.setExternalIdAddress("15l");

        Costumer costumer = new Costumer();
        costumer.setExternalId("19j");

        when(costumerRepository.findByExternalId(costumer.getExternalId())).thenReturn(Optional.of(costumer));
        when(addressRepository.findByExternalIdAddress(address.getExternalIdAddress())).thenReturn(Optional.of(address));

        AddressRequest addressRequest = new AddressRequest("19j","Muarama", "Lavenir da cunha", 12, "MG", "Patos", "12345-459");
        String newneighborhood = "Planalto";

        addressRequest.setBairro(newneighborhood);
        updateAddress.updateAllData("15l", addressRequest);


        Assertions.assertEquals(newneighborhood, addressRequest.getBairro());

    }
}