package org.example.core.useCase.address;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.AddresRequest;
import org.example.core.domain.excecao.NoItemException;
import org.example.core.domain.model.Address;
import org.example.core.domain.model.Costumer;
import org.example.core.port.AddressRepository;
import org.example.core.port.CostumerRepository;
import org.modelmapper.ModelMapper;
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
        newAddress.setNeighborhood(addresRequestDto.getBairro());
        newAddress.setStreet(addresRequestDto.getRuaAvenida());
        newAddress.setNumberHouse(addresRequestDto.getNumeroCasa());
        newAddress.setState(addresRequestDto.getEstado());
        newAddress.setCity(addresRequestDto.getCidade());
        newAddress.setCep(addresRequestDto.getCep());

        return addressRepository.save(newAddress);
    }







}
