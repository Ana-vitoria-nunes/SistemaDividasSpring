package org.example.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {

    private Long id;
    private long idCostumer;
    private String nomeClienteCartao;
    private String numeroCartao;
    private int cvv;

    @JsonFormat(pattern = "MM/yyyy")
    @NotNull
    private YearMonth dataDeValidade;

    @Column(nullable = false)
    private BigDecimal limiteCartao;

}
