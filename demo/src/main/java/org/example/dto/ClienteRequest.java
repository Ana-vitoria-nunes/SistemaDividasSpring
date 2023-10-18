package org.example.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    Long id;
    @NotBlank
    String nomeCompleto;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    LocalDate dataNascimento;

    @Email
    @NotBlank
    String email;

    @NotBlank
    String senha;

    @CPF
    @NotBlank
    String cpf;

    @NotBlank
    String telefone;

}
