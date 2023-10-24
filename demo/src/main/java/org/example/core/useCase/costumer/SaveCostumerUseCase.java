package org.example.core.useCase.costumer;

import lombok.RequiredArgsConstructor;
import org.example.adapters.config.Pass;
import org.example.core.domain.model.dto.ClienteRequest;
import org.example.core.domain.model.Costumer;
import org.example.core.port.CostumerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
    @Transactional
    public Costumer saveCostumer(ClienteRequest dtoCliente) {
        Costumer costumer = mapper.map(dtoCliente, Costumer.class);
        //  String senhaCriptografada = passwordEncoderService.encodePassword(dtoCliente.getSenha(), bCryptPasswordEncoder);
        // costumer.setSenha(pass.encode(dtoCliente.getSenha()));

        String s = Pass.hashPassword(dtoCliente.getSenha());
        costumer.setSenha(s);
        costumer.generateAndSetExternalId();
        costumerRepository.save(costumer);
        return costumer;
    }


}
