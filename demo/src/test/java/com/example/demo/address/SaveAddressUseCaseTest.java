package com.example.demo.address;

import org.example.core.domain.model.Address;
import org.example.core.domain.model.Costumer;
import org.example.core.domain.model.dto.requestDto.AddressRequest;
import org.example.core.port.AddressRepository;
import org.example.core.port.CostumerRepository;
import org.example.core.useCase.address.SaveAddressUseCase;
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
class SaveAddressUseCaseTest {
    @Mock
    private AddressRepository addressRepository;
    @InjectMocks
    private SaveAddressUseCase addressService;
    @Mock
    private CostumerRepository costumerRepository;

    @Test
    @DisplayName("Method to create a new address and should return address created")
    void salvarEndereço() {
        Costumer costumer = new Costumer();
        costumer.setExternalId("12A");

        when(costumerRepository.findByExternalId("12A")).thenReturn(Optional.of(costumer));
        AddressRequest addressRequest = new AddressRequest(costumer.getExternalId(),"Muarama","Lavenir da cunha",12,"MG","Patos","12345-45");
        Address address = new Address();

        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address saved = addressService.saveAddress(addressRequest);
        Assertions.assertEquals(saved,address);
    }

}