package org.example.core.useCase.costumer;

import lombok.RequiredArgsConstructor;
import org.example.adapters.config.Pass;
import org.example.core.domain.model.dto.requestDto.CostumerRequest;
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

    public Costumer saveCostumer(CostumerRequest dtoCliente) {
            Costumer costumer = new Costumer();
            //  String senhaCriptografada = passwordEncoderService.encodePassword(dtoCliente.getSenha(), bCryptPasswordEncoder);
            // costumer.setSenha(pass.encode(dtoCliente.getSenha()));

            String s = Pass.hashCrypto(dtoCliente.getSenha());
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
