package org.example.core.useCase.address;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.AddresRequest;
import org.example.core.domain.excecao.NoItemException;
import org.example.core.domain.model.Address;
import org.example.core.domain.model.Costumer;
import org.example.core.port.AddressRepository;
import org.example.core.port.CostumerRepository;

@RequiredArgsConstructor
public class UpdateAddressUseCase {

    private final AddressRepository addressRepository;
    private final CostumerRepository costumerRepository;

    @Transactional()
    public Address updateAllData(String id, AddresRequest addresRequest) {
        Address address = addressRepository.findByExternalIdAddress(id).orElseThrow(()->
                new NoItemException("Endereço id não encontrado"));

        Costumer costumer1 = costumerRepository.findByExternalId(addresRequest.getIdCostumer()).orElseThrow(() ->
                new NoItemException("id  não encontrado"));

        address.getCostumer();
        address.setNumeroCasa(addresRequest.getNumeroCasa());
        address.setRuaAvenida(addresRequest.getRuaAvenida());
        address.setBairro(addresRequest.getBairro());

        return addressRepository.save(address);
    }




}
