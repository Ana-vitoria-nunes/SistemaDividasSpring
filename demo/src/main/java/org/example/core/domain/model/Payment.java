package org.example.core.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pagamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "externalIdPayment",unique = true)
    private String externalIdPayment;

    @ManyToOne
    @JoinColumn(name = "externalIdCard")
    private Card externalIdCard;

    @OneToMany(mappedBy = "externalIdPayment",fetch = FetchType.LAZY)
    private List<Debts> debts;

    @Column(name = "dataEscolhidaPagamento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dayMoth;

    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private Status stats;

    @Column(nullable = false,name = "valor_Parcela")
    private BigDecimal totalQuota;

    @Column(nullable = false,name = "valor_Emprestimo")
    private BigDecimal totalLending;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dayDebts;

    public void generateAndSetExternalIdPayment() {
        this.externalIdPayment = UUID.randomUUID().toString();
    }
}
