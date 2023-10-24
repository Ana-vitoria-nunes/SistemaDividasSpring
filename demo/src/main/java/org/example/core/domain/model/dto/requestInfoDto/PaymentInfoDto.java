package org.example.core.domain.model.dto.requestInfoDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.core.domain.model.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentInfoDto {
        private String externalId;
        private LocalDateTime dayDebts;
        private BigDecimal totalQuote;
        private BigDecimal  totalLending;
        private int parcelas;
        private Status stats;

}
