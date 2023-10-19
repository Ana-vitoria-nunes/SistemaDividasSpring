package org.example.service;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.ClienteRequest;
import org.example.exceção.NoItemException;
import org.example.model.Costumer;
import org.example.repository.CostumerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClienteService {
        // adicionar cliente OK
        // alterar senha, email, endereço OK
        // colocar um método para confirmar dados que retorna os os dados inseridos (GET)
        // refatorar para ingles OK
        // criar método para logar

    private final CostumerRepository costumerRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper;


    public Costumer saveCostumer (ClienteRequest dtoCliente) {
        Costumer costumer = mapper.map(dtoCliente, Costumer.class);
        costumer.setSenha(encoder.encode(dtoCliente.getSenha()));
        costumerRepository.save(costumer);
        return costumer;
    }

    @Transactional
    public Costumer updateCostumer(ClienteRequest clienteRequest, Long id,String escolha) {
        Costumer newCostumer = costumerRepository.findById(id)
                .orElseThrow(() -> new NoItemException("Id do cliente não encontrado"));

        if (escolha.equals("Email")){
            newCostumer.setEmail(clienteRequest.getEmail());
            return costumerRepository.save(newCostumer);
        } else if (escolha.equals("Senha")) {
            newCostumer.setSenha(clienteRequest.getSenha());
            return costumerRepository.save(newCostumer);
        }else if (escolha.equals("Telefone")){
            newCostumer.setTelefone(clienteRequest.getTelefone());
            return costumerRepository.save(newCostumer);
        }else {
            return null;
        }
    }
//    public void getAllAddress(Lis){
//
//
//    }
}
