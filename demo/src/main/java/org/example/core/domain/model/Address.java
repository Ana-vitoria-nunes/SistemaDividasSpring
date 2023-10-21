package org.example.core.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Table(name = "endereço")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {

    //obs: fazer a validação do endereçopara ser real, procurar por cep e aparecer automático tentar pfvr
    @Id
    @Column(name = "id_Address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Address;

    @NotBlank
    @Column(name = "externalIdAddress",unique = true)
    private String externalIdAddress;

    @OneToOne()
    @JoinColumn(nullable = false, name = "id_externoCliente")
    @NotNull
    private Costumer costumer;

    @NotNull(message = "Número não pode ser nulo")
    @Column(nullable = false, name = "numero_Casa")
    private Integer numberHouse;

    @NotBlank(message = "A rua não pode estar em branco")
    @Column(nullable = false, name = "rua_Avenida")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "O campo rua permite apenas letras ")
    private String street;

    @NotBlank
    @Column(nullable = false)
    @Pattern(regexp = "^[A-Z]{2}$", message = "A sigla do estado deve conter exatamente duas letras maiúsculas")
    private String state;

    @NotBlank(message = "O campo cidade não pode estar em branco")
    @Column(nullable = false,name = "city")
    @Pattern(regexp = "^[a-zA-Z ]*$") //message = "O campo cidade permite apenas letras ")
    private String city;

    @NotBlank()
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Formato de CEP inválido")
    @Column(nullable = false)
    private String cep;

    @NotBlank
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "O bairro permite apenas letras")
    private String neighborhood;

    public void generateAndSetExternalIdAddress() {
        this.externalIdAddress = UUID.randomUUID().toString();
    }
}
