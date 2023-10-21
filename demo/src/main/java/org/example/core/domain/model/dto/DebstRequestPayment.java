package org.example.core.domain.model.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebstRequestPayment {
    private int escolha;

    private String idexternoDebts;
    private String idexternoCard;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate datePaymentChoose;
}
