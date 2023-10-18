package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dividas")
@Entity
public class Debts {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    //colocar a quantidade de zero a mais
    private BigDecimal divida;

    @ManyToOne
    @JoinColumn(name = "id_Cartão")
    private Card idCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Pagamento")
    private Payment idPayment;

}
