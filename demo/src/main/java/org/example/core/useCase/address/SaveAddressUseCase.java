package org.example.core.useCase.address;

import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.requestDto.AddressRequest;
import org.example.core.domain.excecao.NoItemException;
import org.example.core.domain.model.Address;
import org.example.core.domain.model.Costumer;
import org.example.core.port.AddressRepository;
import org.example.core.port.CostumerRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SaveAddressUseCase {

    private final CostumerRepository costumerRepository;
    private final AddressRepository addressRepository;
    public Address saveAddress(AddressRequest addressRequest) {
        Costumer costumer = costumerRepository.findByExternalId(addressRequest.getIdCostumer())
                .orElseThrow(() -> new NoItemException("Id do cliente não encontrado"));

        Address newAddress = new Address();
        newAddress.generateAndSetExternalIdAddress();
        newAddress.setCostumer(costumer);
        newAddress.setNeighborhood(addressRequest.getBairro());
        newAddress.setStreet(addressRequest.getRuaAvenida());
        newAddress.setNumberHouse(addressRequest.getNumeroCasa());
        newAddress.setState(addressRequest.getEstado());
        newAddress.setCity(addressRequest.getCidade());
        newAddress.setCep(addressRequest.getCep());

        return addressRepository.save(newAddress);
    }







}
