package org.example.core.domain.model;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "cartao")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idCard")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCard")
    private Long idCard;

    @NotBlank
    @Column(name = "externalIdCard",unique = true)
    private String externalIdCard;

    @OneToOne
    @JoinColumn(name= "id_externoCliente")
    private Costumer costumer;

    @Column(name = "limite_Cartao",nullable = false)
    private BigDecimal cardLimit;

    @OneToMany(mappedBy = "externalIdCard", fetch = FetchType.EAGER)
    private List<Payment> idPayments;

    @OneToMany(mappedBy = "externalIdCard",cascade = CascadeType.ALL)
    private List<Debts> debts;

    @Column(name = "data_Validade")
    @JsonFormat(pattern = "MM/yyyy")
    private String expiryDate;

    @Column(name = "nome_Cartao", nullable = false,length = 250,unique = true)
    private String nameCostumerCard;

    @Column(nullable = false,name = "numero_Cartao", unique = true)
    private String numberCard;

    @Column(nullable = false,name = "cvv", unique = true)
    private String cvv;

    public void generateAndSetExternalIdCartao() {
        this.externalIdCard = UUID.randomUUID().toString();
    }
}
