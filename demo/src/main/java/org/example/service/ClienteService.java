package org.example.service;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.ClienteRequest;
import org.example.excecao.NoItemException;
import org.example.model.Costumer;
import org.example.repository.CostumerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ClienteService {
    // adicionar cliente OK
    // alterar senha, email, endereço OK
    // colocar um método para confirmar dados que retorna os os dados inseridos (GET)
    // refatorar para ingles
    // criar método para logar

    private final CostumerRepository costumerRepository;
    private final ModelMapper mapper;
    //private final PasswordEncoderServiceRepository passwordEncoderService;
    // private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    public void generateAndSetExternalId(Costumer ) {
//        this.externalId = UUID.randomUUID().toString();
//    }

    public Costumer saveCostumer(@Valid ClienteRequest dtoCliente) {
        Costumer costumer = mapper.map(dtoCliente, Costumer.class);
        //  String senhaCriptografada = passwordEncoderService.encodePassword(dtoCliente.getSenha(), bCryptPasswordEncoder);

        costumer.setSenha(dtoCliente.getSenha());
        costumer.generateAndSetExternalId();
        costumerRepository.save(costumer);
        return costumer;
    }

    @Transactional
    public Costumer updateCostumer(ClienteRequest clienteRequest, String id, String escolha) {
       Costumer newCostumer = costumerRepository.findByExternalId(id)
               .orElseThrow(() -> new NoItemException("Id do cliente não encontrado"));

        if (escolha.equals("Email")) {
            newCostumer.setEmail(clienteRequest.getEmail());
            return costumerRepository.save(newCostumer);
        } else if (escolha.equals("Senha")) {
            newCostumer.setSenha(clienteRequest.getSenha());
            return costumerRepository.save(newCostumer);
        } else if (escolha.equals("Telefone")) {
            return costumerRepository.save(newCostumer);
        } else {
            return null;
        }
    }
//    public Costumer findCustomerByExternalId(String externalId) {
//        Optional<Costumer> customer = costumerRepository.findByExternalId(externalId);
//        if (customer.isPresent()) {
//            return customer.get();
//        } else {
//            throw new NoItemException("Cliente não encontrado");
//        }
//    }
//    public void getAllAddress(Lis){
//
//
//    }
}
