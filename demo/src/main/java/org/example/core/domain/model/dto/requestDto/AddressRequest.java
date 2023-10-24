package org.example.core.domain.model.dto.requestDto;

//import com.example.demo.model.Costumer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class AddressRequest {
    //obs: fazer a validação do endereçopara ser real, procurar por cep e aparecer automático tentar pfvr

    private String idCostumer;

    @NotBlank(message = "O bairro não pode estar em branco")
    private String bairro;

    @NotBlank(message = "A rua não pode estar em branco")
    private String ruaAvenida;

    @Column(nullable = false, name = "numero_Casa")
    @NotNull(message = "Numero da casa não pode estar em branco")
    private Integer numeroCasa;

    @NotBlank(message = "Estado não pode estar em branco")
    private String estado;

    @NotBlank(message = "Cidade não pode estar em branco")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "O campo cidade permite apenas letras ")
    private String cidade;

    @NotBlank
    private String cep;

}
