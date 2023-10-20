package org.example.core.useCase.address;

import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.AddresRequest;
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
    public Address saveAddress(AddresRequest addresRequestDto) {
        Costumer costumer = costumerRepository.findByExternalId(addresRequestDto.getIdCostumer())
                .orElseThrow(() -> new NoItemException("Id do cliente não encontrado"));

        Address newAddress = new Address();

        newAddress.generateAndSetExternalIdAddress();
        newAddress.setCostumer(costumer);
        newAddress.setBairro(addresRequestDto.getBairro());
        newAddress.setRuaAvenida(addresRequestDto.getRuaAvenida());
        newAddress.setNumeroCasa(addresRequestDto.getNumeroCasa());
        newAddress.setEstado(addresRequestDto.getEstado());
        newAddress.setCidade(addresRequestDto.getCidade());
        newAddress.setCep(addresRequestDto.getCep());

        return addressRepository.save(newAddress);
    }







}
