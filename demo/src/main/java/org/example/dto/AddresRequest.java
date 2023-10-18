package org.example.dto;

//import com.example.demo.model.Costumer;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    private Long id;

    private Long idCostumer;

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
