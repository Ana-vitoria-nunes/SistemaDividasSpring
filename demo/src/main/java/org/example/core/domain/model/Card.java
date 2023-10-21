package org.example.core.domain.model;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cartao")
@Data
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

    @OneToMany(mappedBy = "externalIdCard")
    private List<Payment> idPayments;

    @Column(name = "nome_Cartao", nullable = false,length = 250)
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "O nome do cliente permite apenas letras")
    private String nameCostumerCard;

    @Column(nullable = false,name = "numero_Cartao", unique = true)
  //  @Pattern(regexp = "^[0-9]*$", message = "This card it´s only alowed for numbers ")
    private String numberCard;

    @Column(nullable = false,name = "cvv", unique = true)
   // @Pattern(regexp = "^[0-9]{3}$",message = "The entry of cvv it too long" )
    private String cvv;

    @Column(name = "data_Validade")
    @JsonFormat(pattern = "MM/yyyy")
    private String expiryDate;

    @Column(name = "limite_Cartao",nullable = false)
    private BigDecimal cardLimit;

    public void generateAndSetExternalIdCartao() {
        this.externalIdCard = UUID.randomUUID().toString();
    }
}
