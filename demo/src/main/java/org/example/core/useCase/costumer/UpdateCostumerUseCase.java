package org.example.core.useCase.costumer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.CostumerRequest;
import org.example.core.domain.excecao.NoItemException;
import org.example.core.domain.model.Costumer;
import org.example.core.port.CostumerRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateCostumerUseCase {

    private final CostumerRepository costumerRepository;
    @Transactional
    public Costumer updateCostumer(CostumerRequest costumerRequest, String id, String escolha) {
        Costumer newCostumer = costumerRepository.findByExternalId(id)
                .orElseThrow(() -> new NoItemException("Id do cliente não encontrado"));

        if (escolha.equals("Email")) {
            newCostumer.setEmail(costumerRequest.getEmail());
            return costumerRepository.save(newCostumer);
        } else if (escolha.equals("Senha")) {
            newCostumer.setPassword(costumerRequest.getSenha());
            return costumerRepository.save(newCostumer);
        } else if (escolha.equals("Telefone")) {
            newCostumer.setPhone(costumerRequest.getTelefone());
            return costumerRepository.save(newCostumer);
        } else {
            return null;
        }
    }



}
