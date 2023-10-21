package org.example.core.useCase.costumer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.ClienteRequest;
import org.example.core.domain.excecao.NoItemException;
import org.example.core.domain.model.Costumer;
import org.example.core.port.CostumerRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateCostumerUseCase {

    private final CostumerRepository costumerRepository;
    @Transactional
    public Costumer updateCostumer(ClienteRequest clienteRequest, String id, String escolha) {
        Costumer newCostumer = costumerRepository.findByExternalId(id)
                .orElseThrow(() -> new NoItemException("Id do cliente não encontrado"));

        if (escolha.equals("Email")) {
            newCostumer.setEmail(clienteRequest.getEmail());
            return costumerRepository.save(newCostumer);
        } else if (escolha.equals("Senha")) {
            newCostumer.setPassword(clienteRequest.getSenha());
            return costumerRepository.save(newCostumer);
        } else if (escolha.equals("Telefone")) {
            newCostumer.setPhone(clienteRequest.getTelefone());
            return costumerRepository.save(newCostumer);
        } else {
            return null;
        }
    }



}
