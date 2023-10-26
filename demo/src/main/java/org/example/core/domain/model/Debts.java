package org.example.core.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dividas")
@Entity
public class Debts {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "externalIdDebts")
    private String externalIdDebts;

    @Column(nullable = false)
    private BigDecimal debts;

    @ManyToOne()
    @JoinColumn(name = "externalIdCard")
    private Card externalIdCard;

    @ManyToOne()
    @JoinColumn(name = "externalIdPayment")
    private Payment externalIdPayment;

    public void generateAndSetExternalIdDebts() {
        this.externalIdDebts = UUID.randomUUID().toString();
    }

}
