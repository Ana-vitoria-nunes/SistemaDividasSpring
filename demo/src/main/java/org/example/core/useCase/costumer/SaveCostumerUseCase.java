package org.example.core.useCase.costumer;

import ch.qos.logback.core.util.Loader;
import jakarta.transaction.RollbackException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.adapters.config.Pass;
import org.example.core.domain.excecao.ApplicationAdviceController;
import org.example.core.domain.excecao.CustomException;
import org.example.core.domain.model.dto.ClienteRequest;
import org.example.core.domain.model.Costumer;
import org.example.core.port.CostumerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class SaveCostumerUseCase {
    //  private final PasswordEncoderServiceRepository passwordEncoderService;
    // private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    public void generateAndSetExternalId(Costumer ) {
//        this.externalId = UUID.randomUUID().toString();
//    }
//    private final PasswordEncoder pass;

    private final ModelMapper mapper;
    private final CostumerRepository costumerRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public Costumer saveCostumer(ClienteRequest dtoCliente) {
            Costumer costumer = new Costumer();
            //  String senhaCriptografada = passwordEncoderService.encodePassword(dtoCliente.getSenha(), bCryptPasswordEncoder);
            // costumer.setSenha(pass.encode(dtoCliente.getSenha()));
            String s = Pass.hashPassword(dtoCliente.getSenha());
            dtoCliente.setSenha(s);

            costumer.setPassword(dtoCliente.getSenha());
            costumer.generateAndSetExternalId();

            costumer.setPassword(dtoCliente.getSenha());
            costumer.setEmail(dtoCliente.getEmail());
            costumer.setCpf(dtoCliente.getCpf());
            costumer.setName(dtoCliente.getNomeCompleto());
            costumer.setBirthDate(dtoCliente.getDataNascimento());
            costumer.setPhone(dtoCliente.getTelefone());

        costumerRepository.save(costumer);
            return costumer;
    }
}
