package org.example.core.domain.model.dto.requestInfoDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestInfoDto {

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
