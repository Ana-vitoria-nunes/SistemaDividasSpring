package org.example.core.domain.model.dto.requestInfoDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.core.domain.model.dto.requestDto.AddressRequest;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostumerInfoDto {
    private Long idInterno;
    private String nome;
    private String email;
    AddressRequestInfoDto endereço;
    List<PaymentInfoDto> transações;
}
