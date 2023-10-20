package org.example.core.domain.model.dto;

//import com.example.demo.model.Costumer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AddresRequest {
    //obs: fazer a validação do endereçopara ser real, procurar por cep e aparecer automático tentar pfvr

    private String idCostumer;

    @NotBlank
    private String bairro;

    @NotBlank
    private String ruaAvenida;

    @Column(nullable = false, name = "numero_Casa")
    private Integer numeroCasa;

    @NotBlank
    private String estado;

    @NotBlank
    private String cidade;

    @NotBlank
    private String cep;

}
