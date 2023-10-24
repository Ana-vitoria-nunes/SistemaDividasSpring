package org.example.core.useCase.address;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.requestDto.AddressRequest;
import org.example.core.domain.excecao.NoItemException;
import org.example.core.domain.model.Address;
import org.example.core.domain.model.Costumer;
import org.example.core.port.AddressRepository;
import org.example.core.port.CostumerRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateAddressUseCase {

    private final AddressRepository addressRepository;
    private final CostumerRepository costumerRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public Address updateAllData(String id, AddressRequest addressRequest) {
        Address address = addressRepository.findByExternalIdAddress(id).orElseThrow(()->
                new NoItemException("Endereço id não encontrado"));

        Costumer costumer1 = costumerRepository.findByExternalId(addressRequest.getIdCostumer()).orElseThrow(() ->
                new NoItemException("id do Cliente não encontrado"));

        address.getCostumer();
        address.setNumberHouse(addressRequest.getNumeroCasa());
        address.setStreet(addressRequest.getRuaAvenida());
        address.setNeighborhood(addressRequest.getBairro());

        return addressRepository.save(address);
    }




}
