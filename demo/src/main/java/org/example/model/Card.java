package org.example.model;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import java.time.YearMonth;
import java.util.List;

@Entity
@Table(name = "cartao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class    Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name= "id_externoCliente")
    private Costumer costumer;

    @OneToMany(mappedBy = "idCard")
    private List<Payment> idPayments;

    @Column(name = "nome_Cartao", nullable = false,length = 250)
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "O nome do cliente permite apenas letras")
    private String nomeClienteCartao;

    @Column(nullable = false,name = "numero_Cartao", unique = true,length = 16)
    @Pattern(regexp = "^[0-9]*$", message = "A entrada do cartão permite apenas numeros")
    private String numeroCartao;

    @Column(nullable = false,name = "cvv", unique = true)
//    @Min(value = 100, message = "O CVV deve ser um número de 3 dígitos")
//    @Max(value = 999, message = "O CVV deve ser um número de 3 dígitos")
    private String cvv;

    @JsonFormat(pattern = "MM/yyyy")
    @Column(name = "data_Validade")
    @Future(message = "A data de validade do seu cartão deve ser futura")
    private YearMonth dataDevalidade;


    @Column(name = "limite_Cartao",nullable = false)
    private BigDecimal limiteCartao;
}
