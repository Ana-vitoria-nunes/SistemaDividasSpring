package org.example.service;

import com.example.demo.ClienteRequest;
import com.example.demo.exceção.NoItemException;
import com.example.demo.model.Costumer;
import com.example.demo.repository.CostumerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
 //   private final PasswordEncoder encoder;

    public Costumer saveCostumer (ClienteRequest dtoCliente) {
        Costumer costumer = mapper.map(dtoCliente, Costumer.class);
      //  costumer.setSenha(encoder.encode(costumer.getSenha()));
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
