package org.example.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.AddresRequest;
import org.example.exceção.NoItemException;
import org.example.model.Address;
import org.example.model.Costumer;
import org.example.repository.AddressRepository;
import org.example.repository.CostumerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final CostumerRepository costumerRepository;
    private final Costumer costumer;

    //atualizar o numero da casa

    private final ModelMapper mapper;

    public long salvarEndereço(@Valid AddresRequest addresRequestDto) {
     Costumer costumer = costumerRepository.findById(addresRequestDto.getIdCostumer())
            .orElseThrow(() -> new NoItemException("Id do cliente não encontrado"));

        Address newAddress = new Address();

        newAddress.setCostumer(costumer);
        newAddress.setBairro(addresRequestDto.getBairro());
        newAddress.setRuaAvenida(addresRequestDto.getRuaAvenida());
        newAddress.setNumeroCasa(addresRequestDto.getNumeroCasa());
        newAddress.setEstado(addresRequestDto.getEstado());
        newAddress.setCidade(addresRequestDto.getCidade());
        newAddress.setCep(addresRequestDto.getCep());

        newAddress = addressRepository.save(newAddress);

        return newAddress.getId_Endereco();
    }

    @Transactional
    public Address updateAllData(Long id, AddresRequest addresRequest) {
       Address address = addressRepository.findById(id).orElseThrow(()->
               new NoItemException("Endereço id não encontrado"));

       Costumer costumer1 = costumerRepository.findById(addresRequest.getIdCostumer()).orElseThrow(() ->
               new NoItemException("id não encontrado"));

       address.getCostumer();
       address.setNumeroCasa(addresRequest.getNumeroCasa());
       address.setRuaAvenida(addresRequest.getRuaAvenida());
       address.setBairro(addresRequest.getBairro());

       return addressRepository.save(address);
    }
}
//    public Address updateNumber(Long id,String escolha, AddresRequest addresRequest ) {
//        Address address = addressRepository.findById(id).orElseThrow(() -> new NoItemException("Id não encontrado"));
//        if (escolha != null) {
//            if (escolha.equalsIgnoreCase("numero")) {
//                address.setNumeroCasa(addresRequest.getNumeroCasa());
//                return addressRepository.save(address);
//            }
//        } else {
//            throw new NullPointerException();
//        }
//        return address;
//    }





