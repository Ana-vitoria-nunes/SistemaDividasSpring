package org.example.core.useCase.address;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.AddresRequest;
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
    public Address updateAllData(String id, AddresRequest addresRequest) {
        Address address = addressRepository.findByExternalIdAddress(id).orElseThrow(()->
                new NoItemException("Endereço id não encontrado"));

        Costumer costumer1 = costumerRepository.findByExternalId(addresRequest.getIdCostumer()).orElseThrow(() ->
                new NoItemException("id do Cliente não encontrado"));

        address.getCostumer();
        address.setNumberHouse(addresRequest.getNumeroCasa());
        address.setStreet(addresRequest.getRuaAvenida());
        address.setNeighborhood(addresRequest.getBairro());

        return addressRepository.save(address);
    }




}
