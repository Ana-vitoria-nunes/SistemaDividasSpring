package org.example.core.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private BigDecimal valorTotalParcela;
    private BigDecimal valorTotalEmprestimo;
}
