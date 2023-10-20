package org.example.core.domain.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebtsRequest {
    private BigDecimal dividas;
    private String idexternoCard;
}
