package org.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pagamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_Card")
    private Card idCard;

    @OneToMany(mappedBy = "idPayment",fetch = FetchType.LAZY)
    private List<Debts> debts;

    @Column(name = "dataEscolhidaPagamento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dayMoth;

    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false,name = "valor_Parcela")
    private BigDecimal ValorTotalParcela;

    @Column(nullable = false,name = "valor_Emprestimo")
    private BigDecimal valorTotalEmprestimo;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dataHoraDivida;
}
